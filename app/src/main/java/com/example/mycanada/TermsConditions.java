package com.example.mycanada;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class TermsConditions extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);
        webView = findViewById(R.id.webPage1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.pwc.com/ca/en/terms-conditions.html");
    }
}