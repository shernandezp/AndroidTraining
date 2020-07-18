package com.example.mycanada;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    String phone;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ArrayList arrayList = new ArrayList();
        ListView list = findViewById(R.id.listResult);
        imageView = findViewById(R.id.imageView);


        sharedPreferences = getSharedPreferences("mycanadaapp", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        phone = sharedPreferences.getString("phone", "def val");
        arrayList.add(phone);
        arrayList.add(sharedPreferences.getString("blood", "def val"));
        arrayList.add(sharedPreferences.getString("birth", "def val"));
        arrayList.add(sharedPreferences.getString("gender", "def val"));
        arrayList.add(sharedPreferences.getString("qualification", "def val"));

        ArrayAdapter adapter = new ArrayAdapter(Profile.this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: sendSMS(phone); break;
                    case 1: sendMail("sergio.hernandez.p@gmail.com"); break;
                    case 2: break;
                    case 3: break;
                    case 4: break;
                }
            }
        });

    }

    protected void sendSMS(String lineNumber) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(lineNumber, null, "Hello, this is My Canada App", null, null);
        sendNotification("SMS", "Your SMS has been delivered!");
    }

    protected void sendMail(String address) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] {address});
        email.putExtra(Intent.EXTRA_SUBJECT, "Hello Message");
        email.putExtra(Intent.EXTRA_TEXT, "This is My Canada App");

        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client: "));
        sendNotification("Email", "Your Email message has been delivered!");
    }

    protected void sendNotification(String title, String body){
        NotificationManager notificationManager = (NotificationManager) Profile.this.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Profile.this, channelId).setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_media_play).setContentTitle(title).setContentText(body);

        Intent intent1 = new Intent(Profile.this, SignUp.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(Profile.this, 0, intent1, 0);
        mBuilder.setContentIntent(pendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

    public void onImageClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);
        }
    }
}