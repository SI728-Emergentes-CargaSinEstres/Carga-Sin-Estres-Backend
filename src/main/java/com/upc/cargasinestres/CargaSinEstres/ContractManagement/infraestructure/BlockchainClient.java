package com.upc.cargasinestres.CargaSinEstres.ContractManagement.infraestructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.math.BigInteger;

@Component
public class BlockchainClient {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final TransactionManager transactionManager;

    public BlockchainClient(
            @Value("${alchemy.api.url}") String alchemyApiUrl,
            @Value("${ethereum.private.key}") String privateKey,
            @Value("${network.chain.id}") long chainId) {

        if (alchemyApiUrl == null || alchemyApiUrl.isEmpty()) {
            throw new IllegalArgumentException("Alchemy API URL is not configured or is empty.");
        }
        if (privateKey == null || privateKey.isEmpty()) {
            throw new IllegalArgumentException("Ethereum private key is not configured or is empty.");
        }

        // Initialize Web3j client
        try {
            this.web3j = Web3j.build(new HttpService(alchemyApiUrl));
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Web3j client", e);
        }

        // Initialize Credentials
        try {
            this.credentials = Credentials.create(privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Ethereum credentials", e);
        }

        // Estimate gas limit dynamically
        BigInteger estimatedGasLimit;
        try {
            BigInteger gasPrice = Convert.toWei("10", Convert.Unit.GWEI).toBigInteger(); // Set gas price to 5 Gwei

            // Create a sample transaction to estimate gas (dummy transaction data used here for estimation purposes)
            Transaction transaction = Transaction.createEthCallTransaction(
                    credentials.getAddress(),
                    null, // Contract address (null for generic estimation)
                    "0x"); // Data (empty for generic estimation)

            // Estimate the gas limit based on the transaction
            estimatedGasLimit = web3j.ethEstimateGas(transaction).send().getAmountUsed();

            // Optional: Add some buffer to the estimated gas limit if needed
            estimatedGasLimit = estimatedGasLimit.add(BigInteger.valueOf(2000000));

            this.gasProvider = new StaticGasProvider(gasPrice, estimatedGasLimit);

        } catch (Exception e) {
            throw new RuntimeException("Error estimating gas limit", e);
        }

        // Initialize the Transaction Manager with chain ID for replay protection (EIP-155)
        try {
            this.transactionManager = new RawTransactionManager(web3j, credentials, chainId);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing transaction manager", e);
        }
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public ContractGasProvider getGasProvider() {
        return gasProvider;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }
}
