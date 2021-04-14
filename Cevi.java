package com.example.spacebird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Cevi extends OsnovniRazred {
    public static int hitrost;
    public Cevi(float x, float y, int sirina, int visina){
        super(x, y, sirina, visina);
        hitrost = 10*Konstante.ZASLON_SIRINA/1080;  //hitrost cevi iz leve proti desni
    }
    public void draw(Canvas canvas){
        this.x -= hitrost;
        canvas.drawBitmap(this.bm, this.x, this.y, null);
    }

    public void nakljucnoY(){
        Random r = new Random();
        this.y = r.nextInt((0 + this.visina/4) + 1) - this.visina/4;
    }

    @Override
    public void setBm(Bitmap bm) {
        this.bm = Bitmap.createScaledBitmap(bm, sirina, visina, true);
    }
}
