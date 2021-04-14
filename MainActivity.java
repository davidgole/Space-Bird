package com.example.spacebird;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static TextView txt_tocka, txt_tocke_konec;
    public static RelativeLayout rl_konec;
    private UprizoritevIgre ui;
    public static Button gumb_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Pridobivanje zaslona (sirina in visina)
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Konstante.ZASLON_SIRINA = dm.widthPixels;
        Konstante.ZASLON_VISINA = dm.heightPixels;

        setContentView(R.layout.activity_main);

        txt_tocka = findViewById(R.id.txt_tocka);
        txt_tocke_konec = findViewById(R.id.txt_tocke_konec);
        rl_konec = findViewById(R.id.rl_konec);
        gumb_start = findViewById(R.id.gumb_start);
        ui = findViewById(R.id.ui);
        gumb_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ui.setStart(true);
                txt_tocka.setVisibility(View.VISIBLE);
                gumb_start.setVisibility(View.INVISIBLE);
            }
        });
        rl_konec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gumb_start.setVisibility(View.VISIBLE);
                rl_konec.setVisibility(View.INVISIBLE);
                ui.setStart(false);
                ui.reset();
            }
        });
    }
}