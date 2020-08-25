package com.example.pratamajambipelayangan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class Tutorial extends YouTubeBaseActivity
    implements YouTubePlayer.OnInitializedListener{

    public static final String DEVELOPER_KEY = "AIzaSyAwiMSmNFgb-DKuGua-TBy8kJiCB2ZQc-A";
    private String VIDEO_ID;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayerFragment myYouTubePlayerFragment;
    Dialog myDialog;
    private String id;
    private String nama_layanan;
    private String url;
    private String penjelasan;
    private String detail;

    private TextView txtNamaLayanan;
    private TextView txtYoutube;
    private TextView txtPenjelasan;
    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        myYouTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);
        myYouTubePlayerFragment.initialize(DEVELOPER_KEY, this);

        Intent intent = getIntent();
        id = intent.getStringExtra(konfigurasi.EMP_ID);
        nama_layanan = intent.getStringExtra(konfigurasi.TAG_NAMA_LAYANAN);
        penjelasan = intent.getStringExtra(konfigurasi.TAG_PENJELASAN);
        detail = intent.getStringExtra(konfigurasi.TAG_DETAIL);
        url = intent.getStringExtra(konfigurasi.TAG_URL_YOUTUBE);

        txtNamaLayanan = findViewById(R.id.txtNamaLayanan);
        txtNamaLayanan.setText(nama_layanan);

        txtYoutube = findViewById(R.id.txtYoutube);
        txtYoutube.setText(url);

        txtPenjelasan = findViewById(R.id.txtPenjelasan);
        txtPenjelasan.setText(penjelasan);

        txtDetail = findViewById(R.id.txtDetail);
        txtDetail.setText(detail);

        VIDEO_ID = txtYoutube.getText().toString();
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1$s)",
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.youtubeplayerfragment);
    }

}