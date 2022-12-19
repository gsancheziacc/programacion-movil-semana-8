package com.example.gabriel_sanchez_s8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabriel_sanchez_s8.models.News;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int DELAY = 30000;
    private int NotificationId;
    private ArrayList<News> newsArrayList;
    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        newsArrayList = new ArrayList<News>();
        newsArrayList.add(new News("Noticia 01", "Descripción de noticia 01", new Date(2022,12,15)));
        newsArrayList.add(new News("Noticia 02", "Descripción de noticia 02", new Date(2022,12,16)));
        newsArrayList.add(new News("Noticia 03", "Descripción de noticia 03", new Date(2022,12,17)));
        newsArrayList.add(new News("Noticia 04", "Descripción de noticia 04", new Date(2022,12,18)));
        newsArrayList.add(new News("Noticia 05", "Descripción de noticia 05", new Date(2022,12,19)));

        ListView lvNews = findViewById(R.id.lvNews);
        ListAdapter adapter = new ListAdapter(this, newsArrayList);
        lvNews.setAdapter(adapter);

        NotificationId = 0;
    }

    @Override
    protected void onResume() {
        handler.postDelayed(runnable = () -> {
            handler.postDelayed(runnable, DELAY);
            createChannelNotification();
            createNotifications();
        }, DELAY);
        super.onResume();
    }

    private void createChannelNotification(){
        CharSequence name = "Notificacion";
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private void createNotifications()
    {
        int value = ThreadLocalRandom.current().nextInt(0, newsArrayList.size());
        News news = newsArrayList.get(value);
        NotificationId += 1;
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle(news.getTitle());
        builder.setContentText(news.getDescription());
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA,1000,1000);
        builder.setVibrate(new long []{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NotificationId,builder.build());

    }



}