package com.example.huilee.newandroidxproject.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.huilee.newandroidxproject.R;

/**
 * 使用本地广播
 */
public class LocalReceiverActivity extends AppCompatActivity {

   private LocalReceiver localReceiver;
   private IntentFilter intentFilter;
   private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);

        registerBroadReceiver();

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send broadcast receiver
                Intent intent = new Intent("com.example.huilee.newandroidxproject.receiver.LocalReceiver");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    // 注册本地广播
    private void registerBroadReceiver() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localReceiver = new LocalReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.huilee.newandroidxproject.receiver.LocalReceiver");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    /**  本地广播 */
    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "receive local receiver", Toast.LENGTH_LONG).show();
        }
    }


}
