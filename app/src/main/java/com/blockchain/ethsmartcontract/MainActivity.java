package com.blockchain.ethsmartcontract;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Button btn;

    Web3j web3j;

    Boolean conn = false;
    Credentials credentials;

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        web3j = Web3j.build(new HttpService(Utility.ropstenURL));
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
            if (!web3ClientVersion.hasError()) {
                conn = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();


        Log.i("TAG", "onCreate: Web3Clientversion: " + web3ClientVersion);

    }


    @Override
    protected void onStart() {
        super.onStart();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerVoter(Utility.voter, executorService);

            }

        });


    }

    private void registerVoter(String voter, Executor executor) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (conn) {
                    credentials = Credentials.create(Utility.privateKey);

                    ContractGasProvider contractGasProvider = new DefaultGasProvider();

                    //BallotBasic contract = BallotBasic.load("0x536808cf6134aac03750b67ffdb2384df6293028", web3j, credentials, contractGasProvider);
                    BallotBasic contract = BallotBasic.load("0x536808cf6134aac03750b67ffdb2384df6293028", web3j, credentials, GAS_PRICE, new BigInteger("210000"));


                    TransactionReceipt transactionReceipt = null;
                    if (credentials != null && web3j != null) {

                        if (contract != null) {
                            try {
                                transactionReceipt = contract.register(Utility.voter).send();

                                if (transactionReceipt.isStatusOK()) {
                                    Log.d("StatusOK", "onClick: Successful transaction. Gas used: " + transactionReceipt.getGasUsed());
                                    Log.d("StatusOK", "onClick: Transaction Result:" + transactionReceipt.toString());
                                    //Toast.makeText(MainActivity.this, "Successful transaction. Gas used: " + transactionReceipt.getGasUsed(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("StatusNotOk", "onClick: Transaction failed. Gas used");
//                                    Toast.makeText(MainActivity.this, "Transaction failed. Gas used" + transactionReceipt.getGasUsed() +
//                                            "  Transaction Status: " + transactionReceipt.getStatus(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d("Exception", "onClick: On Transaction Failed" + e.getMessage());
                                //Toast.makeText(MainActivity.this, "Transaction failed", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.d("TAG", "onClick: contract var is null");
                        }

                    } else {
                        Log.d("TAG", "onClick: credential or web3 is null Web3: " + web3j + "  Credentials: " + credentials);
                    }


                } else {
                    //Toast.makeText(MainActivity.this, "Not connected with Network", Toast.LENGTH_SHORT).show();
                    Log.d("Connection", "run: Not connected to network");
                }
            }
        });


    }


    private void initViews() {
        btn = findViewById(R.id.button);
        txt = findViewById(R.id.txt);
    }

}