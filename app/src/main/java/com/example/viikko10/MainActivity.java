package com.example.viikko10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WebView web;
    ArrayList<String> osoite = new ArrayList<>(); //Lista johon tallennetaan käyttäjän syöttämä osoite


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);


        Button hae_nappi = (Button) findViewById(R.id.hae_nappi);
        Button paivita_nappi = (Button) findViewById(R.id.paivita);
        Button shoutout_button = (Button) findViewById(R.id.shoutout_button);
        Button initialize_nappi = (Button) findViewById(R.id.initialize_nappi);
        final EditText syote = (EditText) findViewById(R.id.syote);


        hae_nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = syote.getText().toString();
                if (url.equals("index.html")){
                    web.loadUrl("file:///android_asset/index.html");
                    osoite.clear(); //Tyhjennetään aina ennen seuraavaa hakua, jotta osoite löytyis aina kohdasta 0
                    osoite.add("file:///android_asset/index.html");
                }else{
                    url = "http://" + url;
                    osoite.clear(); //Tyhjennetään aina ennen seuraavaa hakua, jotta osoite löytyis aina kohdasta 0
                    osoite.add(url);
                    web.loadUrl(osoite.get(0));
                }
            }
        });

        paivita_nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl(osoite.get(0));
            }
        });

        shoutout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.evaluateJavascript("javascript:shoutOut()", null);
            }
        });

        initialize_nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.evaluateJavascript("javascript:initialize()", null);
            }
        });


    }








}
