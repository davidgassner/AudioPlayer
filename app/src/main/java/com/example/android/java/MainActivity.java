package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ScrollView mScroll;
    private TextView mLog;
    private AudioHelper mAudioHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the ScrollView and TextView for logging
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

    }

    public void runCode(View v) {
        if (mAudioHelper != null) {
            mAudioHelper.stop();
        }
        mAudioHelper = new AudioHelper(this, "musicFile.mp3");
        mAudioHelper.prepareAndPlay();
        logMessage("Playing!");
    }

    public void clearOutput(View v) {
        mLog.setText("");
    }

    public void stopAudio(View view) {
        if (mAudioHelper != null) {
            mAudioHelper.stop();
            logMessage("Stopping!");
        }
    }

    public void logMessage(String s) {
        mLog.append(s + "\n");
        mScroll.scrollTo(0, mScroll.getBottom());
    }
}