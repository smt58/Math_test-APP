package com.LokoTechnology.maingoquizagain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class info extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView share=findViewById(R.id.shareapp);
        TextView share1=findViewById(R.id.shareapp2);
        TextView share2=findViewById(R.id.shareapp3);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1952242653048047/6287521261");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });

        share.setPaintFlags(share.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.loko.quizmats");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent((Intent.ACTION_SEND));
                intent.setType("text/plain");
                String sharebody="TestAPP";
                String shareSub="https://play.google.com/store/apps/details?id=com.loko.translate_app";
                intent.putExtra(Intent.EXTRA_SUBJECT,sharebody);
                intent.putExtra(Intent.EXTRA_TEXT,shareSub);
                startActivity(Intent.createChooser(intent,"Share With"));

            }
        });
        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/dev?id=6906433836992179356");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(info.this,SplashScreen.class);
        startActivity(intent);
        finish();
    }

}