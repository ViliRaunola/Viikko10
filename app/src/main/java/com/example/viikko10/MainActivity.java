package com.example.viikko10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WebView web;
    ArrayList<String> osoite = new ArrayList<>(); //Lista johon tallennetaan käyttäjän syöttämä osoite
    ArrayList<String> edellinen = new ArrayList<>(); //Lista johon tallenetaan käyttäjän edellinen osoite
    ArrayList<String> seuraava = new ArrayList<>(); //Lista johon tulee "seuraava osoite"

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
        Button edellinen_nappi = (Button) findViewById(R.id.edellinen_nappi);
        Button seuraava_nappi = (Button) findViewById(R.id.seuraava_nappi);
        final EditText syote = (EditText) findViewById(R.id.syote);


        hae_nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = syote.getText().toString();
                if (url.equals("index.html")){
                    web.loadUrl("file:///android_asset/index.html");
                    osoite.clear(); //Tyhjennetään aina ennen seuraavaa hakua, jotta osoite löytyis aina kohdasta 0
                    osoite.add("file:///android_asset/index.html");
                    edellinen.add(url);
                }else{
                    url = "http://" + url;
                    osoite.add(url);
                    int vika = osoite.size() - 1;
                    edellinen.add(url);
                    web.loadUrl(osoite.get(vika));
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

        edellinen_nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edellinen.size() == 0){
                    Toast.makeText(getApplicationContext(),"Ei ole edellistä", Toast.LENGTH_SHORT).show();
                }else {
                    int vika = edellinen.size() - 1; //Listaan add();llä on aina lisätty osoite viimeiselle paikalle, joten haetaan nyt listan viimeinen alkio
                    edellinen.remove(vika);
                    if(edellinen.size() == 0){
                        Toast.makeText(getApplicationContext(),"Ei ole edellistä", Toast.LENGTH_SHORT).show();
                    }else {
                        vika = edellinen.size() -1;
                        String url = edellinen.get(vika);
                        seuraava.add(osoite.get(osoite.size() - 1));
                        web.loadUrl(url);
                    }
                }
            }
        });

        seuraava_nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seuraava.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Ei ole seuraavaa", Toast.LENGTH_SHORT).show();
                }else{
                    String url = seuraava.get(seuraava.size() - 1);
                    edellinen.add(osoite.get(osoite.size() - 1));
                    web.loadUrl(url);
                }

            }
        });

    }








}
