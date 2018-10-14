package com.okorkut.progressdialog;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog dialog = new ProgressDialog(this);

        dialog.setTitle("Message");
        dialog.setMessage("please wait..");

        dialog.setCancelable(false);//iptal edilebilirliği kapatıyor, sebebi bir dialogu arka planda işlemler bitene kadar
                                    //göstermek istiyor

        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                }catch (Exception e){
                    e.printStackTrace();
                }

                dialog.cancel();
            }
        }).start();
    }
}
