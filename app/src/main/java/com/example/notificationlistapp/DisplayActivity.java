package com.example.notificationlistapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DisplayActivity extends AppCompatActivity {
    private TextView superHeroNameTextView;
    private ImageView superHeroImage;
    private Button superHeroNotifButton;
    private NotificationManager notificationManager;
    public static final String HERO_CALL_PREF_KEY = "com.example.android.hellosharedprefs";
    public static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    public static final int NOTIFICATION_ID = 0;
    private SharedPreferences heroCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        superHeroNameTextView = findViewById(R.id.superHeroTextViewDisplay);
        superHeroImage = findViewById(R.id.superHeroImageViewDisplay);
        superHeroNotifButton = findViewById(R.id.superHero_NotificationButton);
        heroCall = getSharedPreferences(HERO_CALL_PREF_KEY,MODE_PRIVATE);

        Intent intent = getIntent();
        final String superHeroName = intent.getStringExtra("superHeroName");
        int superHeroImageIntent = intent.getIntExtra("superHeroImage",0);

        superHeroNameTextView.setText(superHeroName);
        Glide.with(this).load(superHeroImageIntent).into(superHeroImage);

        final boolean heroNotifcationAlreadySent = heroCall.contains(superHeroName);

        superHeroNotifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (heroNotifcationAlreadySent){
                    Toast.makeText(getApplicationContext(),"HERO ALREADY HAS BEEN CALLED!",Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences.Editor editor = heroCall.edit();
                    editor.putBoolean(superHeroName,true);
                    editor.apply();
                    sendNotification();

                }

            }
        });
        createNotificationChannel();


    }

    public void sendNotification() {
        NotificationCompat.Builder notifiyBuilder = getNotificationBuilder();
        notificationManager.notify(NOTIFICATION_ID,notifiyBuilder.build());
    }

    public void createNotificationChannel(){
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Super Hero Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from the Superhero");
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,PRIMARY_CHANNEL_ID)
                .setContentTitle("Your Hero Has Been Notified!")
                .setContentText("Your Hero is on the way please wait approximately 2 minutes!")
                .setSmallIcon(R.drawable.ic_superhero_notification_icon)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        return notifyBuilder;
    }


}
