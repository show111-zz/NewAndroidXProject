package com.example.huilee.newandroidxproject.storagedata;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huilee.newandroidxproject.R;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *  file save data
 */
public class FileSaveDataActivity extends AppCompatActivity {

    private EditText etEdit;
    private TextView readData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
        etEdit = findViewById(R.id.et_edit);
        readData = findViewById(R.id.tv_read_data);

        getData();
    }

    public void getData() {
        readData.setText(readData());
        String input = readData();
        if(!TextUtils.isEmpty(input)){
            etEdit.setText(input);
            etEdit.setSelection(input.length());
            Toast.makeText(this,"restoring successed",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String data = etEdit.getText().toString();
        save(data);
    }

    public void save(String inputText){
        FileOutputStream out = null;
        BufferedOutputStream bos = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String readData(){
        FileInputStream fis = null;
        InputStreamReader input = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            fis = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while((line = reader.readLine()) != null ){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


}
