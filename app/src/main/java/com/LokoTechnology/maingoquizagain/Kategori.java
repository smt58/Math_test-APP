package com.LokoTechnology.maingoquizagain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Kategori extends AppCompatActivity {
    Button toplama,cıkarma,carpma,bölme,üslü,köklü;
    private long backPressedTime;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        toplama=findViewById(R.id.toplama);
        cıkarma=findViewById(R.id.cıkarma);
        carpma=findViewById(R.id.carpma);
        bölme=findViewById(R.id.bölme);
        üslü=findViewById(R.id.üslü);
        köklü=findViewById(R.id.köklü);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        toplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Kategori.this,QuizActivity.class);
                intent.putExtra("dbkey",0);
                startActivity(intent);

            }
        });

        cıkarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Kategori.this,QuizActivity.class);
                intent.putExtra("dbkey",1);
                startActivity(intent);

            }
        });

        carpma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Kategori.this,QuizActivity.class);
                intent.putExtra("dbkey",2);
                startActivity(intent);

            }
        });

        bölme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Kategori.this,QuizActivity.class);
                intent.putExtra("dbkey",3);
                startActivity(intent);

            }
        });

        üslü.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Kategori.this,QuizActivity.class);
                intent.putExtra("dbkey",4);
                startActivity(intent);

            }
        });

        köklü.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Kategori.this,QuizActivity.class);
                intent.putExtra("dbkey",5);
                startActivity(intent);

            }
        });



    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            new AlertDialog.Builder(this)
                    .setTitle("Do you  want to exit?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            setResult(RESULT_OK, new Intent().putExtra("Exit", true));
                            finish();
                        }
                    }).create().show();

        }else  {

            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in PlayActivity");
        finish();

    }
}