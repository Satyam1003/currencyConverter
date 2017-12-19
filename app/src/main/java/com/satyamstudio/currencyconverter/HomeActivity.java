package com.satyamstudio.currencyconverter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DownloadTask task = new DownloadTask();
        task.execute("http://apilayer.net/api/live?access_key=d9e35c963f32b9e9fe4c6079abbf99ff&currencies=INR&format=1");
    }



    public class DownloadTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpsURLConnection urlConnection=null;

            try{
                url = new URL(urls[0]);
                urlConnection =(HttpsURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while(data !=-1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    public void Convert(View view)
    {
        EditText Dollar = (EditText)findViewById(R.id.dConvert);
        String s = Dollar.getText().toString();
        if(s!= null) {
        Double dollar = Double.parseDouble(Dollar.getText().toString());
            Double rupee = dollar * 65.28;
            Toast.makeText(getApplicationContext(), "Rs. " + rupee.toString(), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Please, Enter a number", Toast.LENGTH_LONG).show();
        }
    }


}
