package com.example.navigation10;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BrowserActivity extends AppCompatActivity {

    private WebView myWebView;
    private EditText editUrl;

    private static final String HOME_URL = "https://www.yandex.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        myWebView = findViewById(R.id.webView);
        editUrl = findViewById(R.id.editUrl);
        Button btnHome = findViewById(R.id.btnHome);
        Button btnSearch = findViewById(R.id.btnSearch);

        // Настройка WebView
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Загружаем домашнюю страницу
        myWebView.loadUrl(HOME_URL);

        // Кнопка «Домой»
        btnHome.setOnClickListener(v -> myWebView.loadUrl(HOME_URL));

        // Кнопка «Поиск» — загружает URL из поля ввода
        btnSearch.setOnClickListener(v -> {
            String url = editUrl.getText().toString().trim();
            if (!url.isEmpty()) {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://" + url;
                }
                myWebView.loadUrl(url);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
