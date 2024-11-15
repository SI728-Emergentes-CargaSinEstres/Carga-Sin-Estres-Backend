package com.upc.cargasinestres.CargaSinEstres.ContractManagement.infraestructure;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.ReservationScheduledContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

@Service
public class ContractDeployer {
    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;

    @Autowired
    public ContractDeployer(BlockchainClient blockchainClient) {
        this.web3j = blockchainClient.getWeb3j();
        this.credentials = blockchainClient.getCredentials();
        this.gasProvider = blockchainClient.getGasProvider();
    }

    //Dentro de una función -
        //crear contrato en el codigo { }, obteniendo los datos de id de reserva y las fechas //request
        //usar deploy, pasando este objeto creado - devuelva la direccion del contrato
        //guardar esta dirección en un objeto response //response
        //guardar en DB los datos de contrato, del objeto response
    public String deployContract(
            BigInteger reservationId,
            BigInteger registeredDate,
            BigInteger registeredTime) throws Exception {

        // Despliega el contrato
        ReservationScheduledContract contract = ReservationScheduledContract.deploy(
                web3j,
                credentials,
                gasProvider,
                reservationId,
                registeredDate,
                registeredTime).send();

        // Retorna la dirección única del contrato desplegado
        return contract.getContractAddress();
    }
}