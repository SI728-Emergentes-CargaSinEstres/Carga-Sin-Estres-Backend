package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.1.
 */
@SuppressWarnings("rawtypes")
public class ReservationScheduledContract extends Contract {
    public static final String BINARY = "6080604052348015600e575f80fd5b5060405161025a38038061025a8339818101604052810190602e9190607b565b825f81905550816001819055508060028190555050505060c2565b5f80fd5b5f819050919050565b605d81604d565b81146066575f80fd5b50565b5f815190506075816056565b92915050565b5f805f60608486031215608f57608e6049565b5b5f609a868287016069565b935050602060a9868287016069565b925050604060b8868287016069565b9150509250925092565b61018b806100cf5f395ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c80631223f6b21461004e578063c63ae8d91461006e578063cf919ed71461008c578063faf2b9ad146100aa575b5f80fd5b6100566100c8565b60405161006593929190610107565b60405180910390f35b6100766100de565b604051610083919061013c565b60405180910390f35b6100946100e4565b6040516100a1919061013c565b60405180910390f35b6100b26100e9565b6040516100bf919061013c565b60405180910390f35b5f805f8054600154600254925092509250909192565b60025481565b5f5481565b60015481565b5f819050919050565b610101816100ef565b82525050565b5f60608201905061011a5f8301866100f8565b61012760208301856100f8565b61013460408301846100f8565b949350505050565b5f60208201905061014f5f8301846100f8565b9291505056fea26469706673582212200ce1ec8aacd13130d40f74938c0550d3194dc6b09605e27d90e68e13fd4f325b64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETRESERVATIONDETAILS = "getReservationDetails";

    public static final String FUNC_REGISTEREDDATE = "registeredDate";

    public static final String FUNC_REGISTEREDTIME = "registeredTime";

    public static final String FUNC_RESERVATIONID = "reservationId";

    @Deprecated
    protected ReservationScheduledContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
                                           BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ReservationScheduledContract(String contractAddress, Web3j web3j, Credentials credentials,
                                           ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ReservationScheduledContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                                           BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ReservationScheduledContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                                           ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>> getReservationDetails() {
        final Function function = new Function(FUNC_GETRESERVATIONDETAILS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> registeredDate() {
        final Function function = new Function(FUNC_REGISTEREDDATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> registeredTime() {
        final Function function = new Function(FUNC_REGISTEREDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> reservationId() {
        final Function function = new Function(FUNC_RESERVATIONID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static ReservationScheduledContract load(String contractAddress, Web3j web3j, Credentials credentials,
                                                    BigInteger gasPrice, BigInteger gasLimit) {
        return new ReservationScheduledContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ReservationScheduledContract load(String contractAddress, Web3j web3j,
                                                    TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ReservationScheduledContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ReservationScheduledContract load(String contractAddress, Web3j web3j, Credentials credentials,
                                                    ContractGasProvider contractGasProvider) {
        return new ReservationScheduledContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ReservationScheduledContract load(String contractAddress, Web3j web3j,
                                                    TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ReservationScheduledContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ReservationScheduledContract> deploy(Web3j web3j, Credentials credentials,
                                                                  ContractGasProvider contractGasProvider, BigInteger _reservationId,
                                                                  BigInteger _registeredDate, BigInteger _registeredTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reservationId), 
                new org.web3j.abi.datatypes.generated.Uint256(_registeredDate), 
                new org.web3j.abi.datatypes.generated.Uint256(_registeredTime)));
        return deployRemoteCall(ReservationScheduledContract.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<ReservationScheduledContract> deploy(Web3j web3j, TransactionManager transactionManager,
                                                                  ContractGasProvider contractGasProvider, BigInteger _reservationId,
                                                                  BigInteger _registeredDate, BigInteger _registeredTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reservationId), 
                new org.web3j.abi.datatypes.generated.Uint256(_registeredDate), 
                new org.web3j.abi.datatypes.generated.Uint256(_registeredTime)));
        return deployRemoteCall(ReservationScheduledContract.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ReservationScheduledContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
                                                                  BigInteger gasLimit, BigInteger _reservationId, BigInteger _registeredDate,
                                                                  BigInteger _registeredTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reservationId), 
                new org.web3j.abi.datatypes.generated.Uint256(_registeredDate), 
                new org.web3j.abi.datatypes.generated.Uint256(_registeredTime)));
        return deployRemoteCall(ReservationScheduledContract.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ReservationScheduledContract> deploy(Web3j web3j, TransactionManager transactionManager,
                                                                  BigInteger gasPrice, BigInteger gasLimit, BigInteger _reservationId,
                                                                  BigInteger _registeredDate, BigInteger _registeredTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_reservationId), 
                new org.web3j.abi.datatypes.generated.Uint256(_registeredDate), 
                new org.web3j.abi.datatypes.generated.Uint256(_registeredTime)));
        return deployRemoteCall(ReservationScheduledContract.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
