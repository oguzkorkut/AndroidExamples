package com.okorkut.currencyconvert;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    TextView cadText;
    TextView usdText;
    TextView jpyText;
    TextView chfText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadText = findViewById(R.id.cadText);
        usdText = findViewById(R.id.usdText);
        jpyText = findViewById(R.id.jpyText);
        chfText = findViewById(R.id.chfText);
    }

    public void getRates(View view){

        DownloadData downloadData = new DownloadData();

        try {
            String url = "http://data.fixer.io/api/latest?access_key=08be1d0c5413250c339d56d6378639bb&format=1";

            downloadData.execute(url);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Arka planda app kilitlenmeden indirir
     *
     *1.Parametre : url
     *2.Parametre : progress tarzı
     *3.Parametre return parametre
     */
    private class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();

                }


                return result;

            } catch (Exception e) {
                return null;
            }
        }

        /**
         * işlem bittikten sonra çalışır
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println("JSON DATA: " + s);

            try{
                JSONObject jsonObject = new JSONObject(s);

                String base = jsonObject.getString("base");

                System.out.println("Base: " + base);

                String rates = jsonObject.getString("rates");

                System.out.println("Rates: " + rates);

                JSONObject jsonObject1 = new JSONObject(rates);

                String cad = jsonObject1.getString("CAD");
                System.out.println("CAD: " + cad);

                cadText.setText("CAD: " + cad);

                String usd = jsonObject1.getString("USD");
                System.out.println("USD: " + usd);

                usdText.setText("USD: " + usd);

                String jpy = jsonObject1.getString("JPY");
                System.out.println("JPY: " + jpy);

                jpyText.setText("JPY: " + jpy);

                String chf = jsonObject1.getString("CHF");
                System.out.println("CHF: " + chf);

                chfText.setText("CHF: " + chf);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
