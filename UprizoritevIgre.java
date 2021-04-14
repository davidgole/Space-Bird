package com.example.spacebird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.logging.LogRecord;

public class UprizoritevIgre extends View {
    private Ptica ptica;
    private Handler handler;
    private Runnable runnable;
    private ArrayList<Cevi> arrCev;
    private int sumCev, razmaky;
    private int tocke;
    private boolean start;
    public UprizoritevIgre(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tocke = 0;
        start = false;
        ustvariPtico();
        ustvariCev();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();   //posodobi metodo draw()
            }
        };
    }

    private void ustvariCev() {
        sumCev = 6;
        razmaky = 400*Konstante.ZASLON_VISINA/1920;
        arrCev = new ArrayList<>();
        for (int i=0; i<sumCev; i++){
            if (i<sumCev/2){
                this.arrCev.add(new Cevi(Konstante.ZASLON_SIRINA + i*((Konstante.ZASLON_SIRINA + 200*Konstante.ZASLON_SIRINA/1080)/(sumCev/2)),
                        0, 200*Konstante.ZASLON_SIRINA/1080, Konstante.ZASLON_VISINA/2));
                this.arrCev.get(this.arrCev.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.cev2));
                this.arrCev.get(this.arrCev.size()-1).nakljucnoY();
            }else{
                this.arrCev.add(new Cevi(this.arrCev.get(i - sumCev/2).getX(), this.arrCev.get(i - sumCev/2).getY() + this.arrCev.get(i - sumCev/2).getVisina() + this.razmaky,
                        200*Konstante.ZASLON_SIRINA/1080, Konstante.ZASLON_VISINA/2));
                this.arrCev.get(this.arrCev.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.cev1));

            }
        }
    }

    private void ustvariPtico() {
        ptica = new Ptica();
        ptica.setSirina(100*Konstante.ZASLON_SIRINA/1080);
        ptica.setVisina(100*Konstante.ZASLON_VISINA/1920);
        ptica.setX(100*Konstante.ZASLON_VISINA/1080);
        ptica.setY(Konstante.ZASLON_VISINA/2-ptica.getVisina()/2);
        ArrayList<Bitmap> arrBm = new ArrayList<>();
        arrBm.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ptica1));
        arrBm.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.ptica21));
        ptica.setArrBm(arrBm);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        if (start){
            ptica.draw(canvas);
            for (int i=0; i<sumCev; i++){
                if (ptica.getRect().intersect(arrCev.get(i).getRect()) || ptica.getY()-ptica.getVisina()<0 || ptica.getY()>Konstante.ZASLON_VISINA){
                    Cevi.hitrost = 0;
                    MainActivity.txt_tocke_konec.setText(MainActivity.txt_tocka.getText());
                    MainActivity.txt_tocka.setVisibility(INVISIBLE);
                    MainActivity.rl_konec.setVisibility(VISIBLE);
                }
                if(this.ptica.getX()+this.ptica.getSirina() > arrCev.get(i).getX()+arrCev.get(i).getSirina()/2
                        && this.ptica.getX()+this.ptica.getSirina() <= arrCev.get(i).getX()+arrCev.get(i).getSirina()/2+
                        Cevi.hitrost && i<sumCev/2){
                    tocke++;
                    MainActivity.txt_tocka.setText(""+tocke);
                }

                if(this.arrCev.get(i).getX() < -arrCev.get(i).getSirina()){
                    this.arrCev.get(i).setX(Konstante.ZASLON_SIRINA);
                    if(i < sumCev/2){
                        arrCev.get(i).nakljucnoY();
                    }else{
                        arrCev.get(i).setY(this.arrCev.get(i - sumCev/2).getY() + this.arrCev.get(i - sumCev/2).getVisina() + this.razmaky);
                    }
                }
                this.arrCev.get(i).draw(canvas);
            }
        }else{
            if (ptica.getY()>Konstante.ZASLON_VISINA/2){
                ptica.setPadec(-15*Konstante.ZASLON_VISINA/1920);
            }
            ptica.draw(canvas);
        }
        handler.postDelayed(runnable, 10);  //posodobitev vsakih 10 milisekund
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            ptica.setPadec(-15);
        }
        return true;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void reset() {
        MainActivity.txt_tocka.setText("0");
        tocke = 0;
        ustvariPtico();
        ustvariCev();
    }
}
