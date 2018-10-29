package com.example.huilee.newandroidxproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.huilee.newandroidxproject.CProvider.UserPermissionActivity;
import com.example.huilee.newandroidxproject.notification.MyNotificationActivity;
import com.example.huilee.newandroidxproject.receiver.BroadActivity;
import com.example.huilee.newandroidxproject.receiver.LocalReceiverActivity;
import com.example.huilee.newandroidxproject.storagedata.FileSaveDataActivity;
import com.example.huilee.newandroidxproject.storagedata.SharedPreferenceActivity;
import com.example.huilee.newandroidxproject.storagedata.SqlDataSaveActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LHF","MainActivity");
        setContentView(R.layout.activity_main);
        Log.d("LHF","on create"); // 第一次调用

        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.hide();
        }


        progressBar = findViewById(R.id.pb_bar);

        if(savedInstanceState != null){
            String tempData = savedInstanceState.getString("data_key");
            Log.d("Lhf", tempData);
        }

        findViewById(R.id.btn_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BroadActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyNotificationActivity.class);
                startActivity(intent);

//                Intent intent = new Intent(MainActivity.this,UserPermissionActivity.class);
//                startActivity(intent);

//                Intent intent = new Intent(MainActivity.this, SqlDataSaveActivity.class);
//                startActivity(intent);

//                Intent intent = new Intent(MainActivity.this, SharedPreferenceActivity.class);
//                startActivity(intent);

//                if(progressBar.getVisibility() == View.VISIBLE){
//                    progressBar.setVisibility(View.GONE);
//                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                }

//                int progress = progressBar.getProgress();
//                progress+=10;
//                progressBar.setProgress(progress);

//                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
//                dialog.setTitle("this is title");
//                dialog.setMessage("Loading");
//                dialog.setCancelable(true);
//                dialog.show();

            }
        });

        findViewById(R.id.btn_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LocalReceiverActivity.class);
                startActivity(intent);
            }
        });


    }

    // 在活动回收之前被调用，解决活动被回收临时数据得不到保存的问题
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String temData = "Something you just type";
        outState.putString("data_key",temData);
    }

    // 不可见变为可见
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LHF","on start");
    }

    // 和用户交互，且一定为栈顶运行状态
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LHF","on resume");
    }

    // 启动另一个活动时调用
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LHF","on pause");
    }

    //  完全不可见
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LHF","on stop");
    }

    // 活动销毁之前调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LHF","on destroy");
    }

    // 被重新启动。
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LHF","on reStart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "you click add",Toast.LENGTH_LONG).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you click remove",Toast.LENGTH_LONG).show();
                break;
                default:
                    break;
        }
        return true;
    }

    // finish()用来销毁一个活动 和back键一样的效果

//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.right_layout,fragment);
//        transaction.addToBackStack(null); // 将一个事务添加到返回栈中
//        transaction.commit();
//    }

}


