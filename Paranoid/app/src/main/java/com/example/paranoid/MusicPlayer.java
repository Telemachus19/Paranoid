package com.example.paranoid;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicPlayer extends Service {
    public MusicPlayer() {
    }

    private MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand (Intent intent , int flags , int startID){
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.m);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer.stop();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}