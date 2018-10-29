package com.example.huilee.newandroidxproject.CProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huilee.newandroidxproject.R;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by huilee
 * Created date on 2018-10-28
 *  read the phone contacts
 */
public class ProviderDataActivity extends AppCompatActivity {

    ListView contactsView;
    ArrayAdapter<String> adapter;
    List<String> contactsList = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_permission_layout);

        contactsView = findViewById(R.id.lv_contact);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }

    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    contactsList.add(displayName + "\n" + number);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if(grantResults.length >0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}
