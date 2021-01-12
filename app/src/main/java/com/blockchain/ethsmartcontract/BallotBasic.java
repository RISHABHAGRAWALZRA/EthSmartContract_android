package com.blockchain.ethsmartcontract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class BallotBasic extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516103053803806103058339818101604052602081101561003357600080fd5b5051600080546001600160a01b03191633178082556001600160a01b0316815260016020526040902060029081905560ff8216906100719082610078565b50506100c2565b81548183558181111561009c5760008381526020902061009c9181019083016100a1565b505050565b6100bf91905b808211156100bb57600081556001016100a7565b5090565b90565b610234806100d16000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80634420e48614610046578063609ff1bd1461006e578063b3f98adc1461008c575b600080fd5b61006c6004803603602081101561005c57600080fd5b50356001600160a01b03166100ac565b005b610076610117565b6040805160ff9092168252519081900360200190f35b61006c600480360360208110156100a257600080fd5b503560ff1661017f565b6000546001600160a01b0316331415806100e257506001600160a01b0381166000908152600160208190526040909120015460ff165b156100ec57610114565b6001600160a01b038116600090815260016020819052604090912081815501805460ff191690555b50565b600080805b60025460ff8216101561017a578160028260ff168154811061013a57fe5b906000526020600020016000015411156101725760028160ff168154811061015e57fe5b906000526020600020016000015491508092505b60010161011c565b505090565b3360009081526001602081905260409091209081015460ff16806101a8575060025460ff831610155b156101b35750610114565b6001818101805460ff191690911761ff00191661010060ff8516908102919091179091558154600280549192909181106101e957fe5b600091825260209091200180549091019055505056fea265627a7a72315820fd513ca1c36eed9e19386414682b625f1db7ba3f24bb330ecf2d5d2c26c0bf5564736f6c63430005100032";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_WINNINGPROPOSAL = "winningProposal";

    @Deprecated
    protected BallotBasic(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BallotBasic(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BallotBasic(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BallotBasic(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> register(String toVoter) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, toVoter)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger toProposal) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(toProposal)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> winningProposal() {
        final Function function = new Function(FUNC_WINNINGPROPOSAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static BallotBasic load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BallotBasic(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BallotBasic load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BallotBasic(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BallotBasic load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BallotBasic(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BallotBasic load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BallotBasic(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BallotBasic> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_numProposals)));
        return deployRemoteCall(BallotBasic.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<BallotBasic> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_numProposals)));
        return deployRemoteCall(BallotBasic.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<BallotBasic> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_numProposals)));
        return deployRemoteCall(BallotBasic.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<BallotBasic> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _numProposals) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_numProposals)));
        return deployRemoteCall(BallotBasic.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
