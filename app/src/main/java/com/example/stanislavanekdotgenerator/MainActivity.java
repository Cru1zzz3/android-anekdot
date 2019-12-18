package com.example.stanislavanekdotgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.*;
import java.io.*;

import android.util.Log;

// Jsoup import
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Element;


import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAnekdot = findViewById(R.id.buttonAnekdot);

        buttonAnekdot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView textAnekdot = findViewById(R.id.textAnekdot);

                new Content().execute();

            }
        });


    }

    public class Content extends AsyncTask<Void, Void, Void> {
        String title;
        String anekdot;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String url = "https://www.anekdot.ru/random/anekdot/";
                Document doc = Jsoup.connect(url).get();
                //doc = Jsoup.parse(String.valueOf(doc));
                anekdot = doc.select(".text").first().text();
                title = doc.title();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Document doc = ;
            //Elements anekdot = doc.select("text");
            TextView textAnekdot = findViewById(R.id.textAnekdot);
            textAnekdot.setText(String.valueOf(anekdot));
        }
    }
}
