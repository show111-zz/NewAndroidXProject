package com.example.huilee.newandroidxproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LHF","on create"); // 第一次调用

        findViewById(R.id.btn_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
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

}
