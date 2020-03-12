package com.sarang.videoview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String LINK = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(LINK);
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.requestFocus();
        videoView.start();

    }
}
