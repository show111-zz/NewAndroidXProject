package com.example.huilee.newandroidxproject.storagedata;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.huilee.newandroidxproject.R;

public class SharedPreferenceActivity extends PreferenceActivity {

    private Button saveData ;
    private Button restoreData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
//        getSP();

        saveData = findViewById(R.id.btn1);
        restoreData = findViewById(R.id.btn2);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sp 存储数据的方式
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name","huilee");
                editor.putInt("age",18);
                editor.putBoolean("study",true);
                editor.apply();
            }
        });

        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp2 = getSharedPreferences("data", MODE_PRIVATE);
                String name = sp2.getString("name","");
                int age = sp2.getInt("age",0);
                boolean isStudy = sp2.getBoolean("study",false);
                Log.d("LHF","name is: "+name+"age is "+age +"isStudy : " +isStudy);
            }
        });
    }

    private void getSP() {
        // 1. context 中的 getSharedPreferences ()
        SharedPreferences data_sp = getSharedPreferences("data_sp", MODE_PRIVATE);

        // 2. activity 中的 getPreferences ()
        SharedPreferences  sp = this.getPreferences(MODE_PRIVATE);

        // 3. PreferenceManager类中的getSharedPreferences()
        PreferenceManager manager = getPreferenceManager();
        SharedPreferences manager_sp = manager.getSharedPreferences();
    }

}
