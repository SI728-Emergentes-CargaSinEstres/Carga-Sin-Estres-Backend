package com.upc.cargasinestres.CargaSinEstres.Contract.service;

import com.upc.cargasinestres.CargaSinEstres.Contract.shared.ContractFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.io.IOException;

@Service
public class ContractService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractFileReader contractFileReader;

    public ContractService(@Value("${alchemy.api.url}") String alchemyApiUrl,
                           @Value("${ethereum.private.key}") String privateKey,
                           ContractFileReader contractFileReader) {
        this.web3j = Web3j.build(new HttpService(alchemyApiUrl));
        this.credentials = Credentials.create(privateKey);
        this.contractFileReader = contractFileReader;
    }

    private String readBytecodeFromFile() throws IOException {
        return contractFileReader.getBytecode();
    }

    public String deployContrato(@Value("${alchemy.api.url}") String alchemyApiUrl) throws Exception {
        contractFileReader = new ContractFileReader();
        this.web3j = Web3j.build(new HttpService(alchemyApiUrl));
        credentials = Credentials.create(@Value("${ethereum.private.key}")); //falta corregir, pero es el valor de la llave en app properties el que se debe pasar.

        return deployContract(readBytecodeFromFile(), web3j, credentials);
    }


    private String deployContract(String bytecode, Web3j web3j, Credentials credentials) throws Exception {
        // Aquí iría la lógica de despliegue del contrato utilizando Web3J
        TransactionReceipt receipt = deployContract(bytecode, web3j, credentials, new DefaultGasProvider());
        return receipt.getContractAddress();
    }

    // Método para desplegar el contrato (ejemplo) //parece que el SimpleStorage esta haciendo referencia al contrato que se va a desplegar, debe cambiarse.
    public static RemoteCall<SimpleStorage> deployContract(String bytecode, Web3j web3j, Credentials credentials, GasProvider gasProvider) {
        return SimpleStorage.deploy(web3j, credentials, gasProvider, bytecode);
    }


    // Método para verificar la conexión obteniendo la versión del cliente de Ethereum
    public String getClientVersion() throws IOException {
        Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
        return clientVersion.getWeb3ClientVersion();
    }
}
