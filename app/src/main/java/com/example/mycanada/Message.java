package com.example.mycanada;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class Message extends AppCompatActivity {

    EditText phone;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        phone = findViewById(R.id.phoneNumber);
        message = findViewById(R.id.textMessage);

    }

    public void onSendMessage(View view) {
        sendSMS(phone.getText().toString(), message.getText().toString());
        finish();
    }

    protected void sendSMS(String lineNumber, String Message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(lineNumber, null, Message, null, null);
        sendNotification("SMS", "Your SMS has been delivered!");
    }

    protected void sendNotification(String title, String body) {
        NotificationManager notificationManager = (NotificationManager) Message.this.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Message.this, channelId).setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_media_play).setContentTitle(title).setContentText(body);

        Intent intent1 = new Intent(Message.this, SignUp.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(Message.this, 0, intent1, 0);
        mBuilder.setContentIntent(pendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }
}