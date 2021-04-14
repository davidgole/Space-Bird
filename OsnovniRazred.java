package com.example.spacebird;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class OsnovniRazred {
    protected float x, y;
    protected int sirina, visina;
    protected Rect rect;
    protected Bitmap bm;

    public OsnovniRazred() {
    }

    public OsnovniRazred(float x, float y, int sirina, int visina) {
        this.x = x;
        this.y = y;
        this.sirina = sirina;
        this.visina = visina;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getSirina() {
        return sirina;
    }

    public void setSirina(int sirina) {
        this.sirina = sirina;
    }

    public int getVisina() {
        return visina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Rect getRect() {
        return new Rect((int)this.x, (int)this.y, (int)this.x+(int)this.sirina, (int)this.y+(int)this.visina);
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
