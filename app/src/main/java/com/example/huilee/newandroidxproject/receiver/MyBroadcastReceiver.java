package com.example.huilee.newandroidxproject.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
/**
 *  自定义广播
 */
public class MyBroadcastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"receive my broadcast receiver", Toast.LENGTH_LONG).show();
//        abortBroadcast(); // 截断广播
    }

}
