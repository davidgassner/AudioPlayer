package com.example.android.java;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.IOException;

public class AudioHelper {

    private Context context;
    private String audioFile;
    private MediaPlayer mediaPlayer;

    public AudioHelper(Context context, String audioFile) {
        this.context = context;
        this.audioFile = audioFile;
    }

    public void prepareAndPlay() {

        String fullFileName =
                FileHelper.copyAssetToCache(context, audioFile);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                play();
            }
        });
        FileInputStream stream = null;

        try {
            stream = new FileInputStream(fullFileName);
            mediaPlayer.setDataSource(stream.getFD());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
        } catch (IOException e) {
            return;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

    public void play() {
        mediaPlayer.start();
    }

    //  Stop this cue and set its player object to null
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
