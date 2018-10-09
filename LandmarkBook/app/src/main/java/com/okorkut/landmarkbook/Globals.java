package com.okorkut.landmarkbook;

import android.graphics.Bitmap;

public class Globals {

    private Bitmap choosenImage;

    private static Globals instanse;

    public Bitmap getChoosenImage() {
        return choosenImage;
    }

    public void setChoosenImage(Bitmap choosenImage) {
        this.choosenImage = choosenImage;
    }


    public static Globals getInstanse() {

        if (instanse == null){
            instanse =  new Globals();
        }
        return instanse;
    }
}
