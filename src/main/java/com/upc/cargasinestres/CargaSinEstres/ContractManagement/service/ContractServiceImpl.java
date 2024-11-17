package com.upc.cargasinestres.CargaSinEstres.ContractManagement.service;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.request.ContractRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.response.ContractResponseDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.Contract;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.ReservationScheduledContract;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository.IContractRepository;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.service.IContractService;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.infraestructure.BlockchainClient;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.dto.Customer.response.CustomerResponseDto;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
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
                    blockchainClient.getTransactionManager(),
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

            var newContract = modelMapper.map(contractRequestDto, Contract.class);
            newContract.setReservationId(contractRequestDto.getReservationId());
            newContract.setHashCodeValue(contractAddress);
            newContract.setRegisteredDate(currentDate);
            newContract.setRegisteredTime(java.sql.Time.valueOf(currentTime));
            var createdContract = contractRepository.save(newContract);

            return modelMapper.map(createdContract, ContractResponseDto.class);
        } catch (Exception e) {
            logger.error("Error occurred while creating the contract for reservationId: {}", contractRequestDto.getReservationId(), e);
            throw new RuntimeException("Error deploying contract", e);
        }
    }

    @Override
    public ContractResponseDto getContractByReservationId(Long reservationId) {
        var contract = contractRepository.findTopByReservationIdOrderByIdDesc(reservationId);
        if (contract.isEmpty()) {
            throw new ResourceNotFoundException("No existe un contrato con el id de reserva " + reservationId);
        }

        return modelMapper.map(contract.get(), ContractResponseDto.class);
    }


}
