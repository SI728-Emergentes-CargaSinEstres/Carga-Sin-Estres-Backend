package com.upc.cargasinestres.CargaSinEstres.ContractManagement.service;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.request.ContractRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.response.ContractResponseDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.ReservationScheduledContract;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository.IContractRepository;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.service.IContractService;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.infraestructure.BlockchainClient;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.dto.Customer.response.CustomerResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class ContractServiceImpl implements IContractService {

    private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

    private final BlockchainClient blockchainClient;
    private final IContractRepository contractRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContractServiceImpl(IContractRepository contractRepository, ModelMapper modelMapper, BlockchainClient blockchainClient) {
        this.contractRepository = contractRepository;
        this.modelMapper = modelMapper;
        this.blockchainClient = blockchainClient;
    }

    public String deployContract(
            BigInteger reservationId,
            BigInteger registeredDate,
            BigInteger registeredTime) throws Exception {

        logger.info("Deploying contract with reservationId: {}, registeredDate: {}, registeredTime: {}",
                reservationId, registeredDate, registeredTime);

        try {
            ReservationScheduledContract contract = ReservationScheduledContract.deploy(
                    blockchainClient.getWeb3j(),
                    blockchainClient.getTransactionManager(), // Use transaction manager with chain ID
                    blockchainClient.getGasProvider(),
                    reservationId,
                    registeredDate,
                    registeredTime).send();

            String contractAddress = contract.getContractAddress();
            logger.info("Contract deployed successfully at address: {}", contractAddress);

            return contractAddress;

        } catch (Exception e) {
            logger.error("Failed to deploy contract on the blockchain", e);
            throw new RuntimeException("Error deploying contract on the blockchain", e);
        }
    }

    @Override
    public ContractResponseDto createContract(ContractRequestDto contractRequestDto) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        logger.info("Creating contract for reservationId: {}", contractRequestDto.getReservationId());

        try {
            // Deploy the contract and retrieve its address
            String contractAddress = deployContract(
                    BigInteger.valueOf(contractRequestDto.getReservationId()),
                    BigInteger.valueOf(currentDate.toEpochDay()),
                    BigInteger.valueOf(currentTime.toSecondOfDay())
            );

            logger.info("Mapping contract request to response DTO");
            ContractResponseDto response = modelMapper.map(contractRequestDto, ContractResponseDto.class);
            response.setReservationId(contractRequestDto.getReservationId());
            response.setHashCodeValue(contractAddress);
            response.setRegisteredDate(currentDate);
            response.setRegisteredTime(java.sql.Time.valueOf(currentTime));

            logger.info("Contract created and response DTO generated for reservationId: {}", contractRequestDto.getReservationId());

            return response;

        } catch (Exception e) {
            logger.error("Error occurred while creating the contract for reservationId: {}", contractRequestDto.getReservationId(), e);
            throw new RuntimeException("Error deploying contract", e);
        }
    }

    @Override
    public ContractResponseDto getContractByReservationId(Long reservationId) {
        var contract = contractRepository.findByReservationId(reservationId);
        if (contract.isEmpty())
            throw new ResourceNotFoundException("No existe un contrato con el id de reserva " + reservationId.toString()); // se valida

        return modelMapper.map(contract, ContractResponseDto.class);
    }
}
