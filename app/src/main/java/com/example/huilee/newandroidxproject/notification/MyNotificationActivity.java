package com.example.huilee.newandroidxproject.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.huilee.newandroidxproject.MainActivity;
import com.example.huilee.newandroidxproject.R;

/*
 * Created by huilee
 * Created date on 2018-10-29
 * Notification Knowledge
 */
public class MyNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    Button notificationBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_permission_layout);

        notificationBtn = findViewById(R.id.btn_call);
        notificationBtn.setOnClickListener(this);

        // cancel the notification when clicked
//       NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//       manager.cancel(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                notifyInfo();
                break;
            default:
                break;
        }
    }

    private void notifyInfo() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);

       NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText("" +
//                        "Learn how to build notifications, send and sync data, and use voice actions." +
//                        "Get the official Android IDE and developer tools to build app for Android."))
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                        BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)))
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setAutoCancel(true)   // cancel the notification when clicked
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        manager.notify(1, notification);
    }

}
