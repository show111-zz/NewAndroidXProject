package com.example.huilee.newandroidxproject.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.huilee.newandroidxproject.R;

/**
 *  接收系统广播
 */
public class BroadActivity extends AppCompatActivity {

    NetworkChangeReceiver networkChangeReceiver;
    IntentFilter intentFilter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);

        registerBroadReceiver();

        // send my broadcast receiver
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.huilee.newandroidxproject.receiver.MyBroadcastReceiver");
                sendBroadcast(intent);
//                sendOrderedBroadcast(intent,null);
            }
        });

    }

    // register network broadcast receiver
    private void registerBroadReceiver() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregister broadcast receiver
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnected()){
                Toast.makeText(context,"network is conneted",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"network is disconneted",Toast.LENGTH_LONG).show();
            }
        }
    }


}
