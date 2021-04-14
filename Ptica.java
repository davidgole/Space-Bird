package com.example.spacebird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

public class Ptica extends OsnovniRazred {
    private ArrayList<Bitmap> arrBm = new ArrayList<>();
    private int stetje, yZamah, idTrenutniBitmap;   //idTrenutniBitmap je trenutni Bitmap uporabljen v arrBm
    private float padec;
    public Ptica() {
        this.stetje = 0;
        this.yZamah = 5;
        this.idTrenutniBitmap = 0;
        this.padec = 0;
    }
    public void draw(Canvas canvas){
        gravitacija();
        //risanje bitmap na canvas
        canvas.drawBitmap(this.getBm(), this.x, this.y, null);
    }

    private void gravitacija() {
        this.padec += 0.6;
        this.y += this.padec;
    }

    public ArrayList<Bitmap> getArrBm() {
        return arrBm;
    }

    public void setArrBm(ArrayList<Bitmap> arrBm) {
        this.arrBm = arrBm;
        //we need to scale those bitmaps to the height and width of the bird
        for (int i=0; i<arrBm.size(); i++){
            this.arrBm.set(i, Bitmap.createScaledBitmap(this.arrBm.get(i),this.sirina,this.visina, true));
        }
    }

    @Override
    public Bitmap getBm() {
        //OGENJ NA PTICO (menjava dveh slikic)
        stetje++;
        if(this.stetje == this.yZamah){
            for (int i=0; i<arrBm.size(); i++){
                if(i == arrBm.size()-1){
                    this.idTrenutniBitmap = 0;
                    break;
                }else if (this.idTrenutniBitmap == i){
                    idTrenutniBitmap = i+1;
                    break;
                }
            }
            stetje = 0;
        }
        //ANIMACIJA PTIČA PRI LETU
        if(this.padec < 0){
            Matrix m = new Matrix();
            m.postRotate(-25);
            return Bitmap.createBitmap(arrBm.get(idTrenutniBitmap), 0, 0, arrBm.get(idTrenutniBitmap).getWidth(), arrBm.get(idTrenutniBitmap).getHeight(), m, true);
        }else if (padec >= 0){
            Matrix m = new Matrix();
            if(padec < 70)
                m.postRotate(-25+(padec*2));
            else
                m.postRotate(45);
            return Bitmap.createBitmap(arrBm.get(idTrenutniBitmap), 0, 0, arrBm.get(idTrenutniBitmap).getWidth(), arrBm.get(idTrenutniBitmap).getHeight(), m, true);
        }

        return this.arrBm.get(idTrenutniBitmap);

    }

    public float getPadec() {
        return padec;
    }

    public void setPadec(float padec) {
        this.padec = padec;
    }
}
