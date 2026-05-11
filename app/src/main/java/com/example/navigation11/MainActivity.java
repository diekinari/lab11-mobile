package com.example.navigation11;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean flashlightIsOn = false;
    private CameraManager cameraManager;
    private String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация камеры для фонарика
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        // --- Кнопка: Телефон ---
        Button btnPhone = findViewById(R.id.btnPhone);
        btnPhone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:+79261234567"));
            startActivity(intent);
        });

        // --- Кнопка: Браузер ---
        Button btnBrowser = findViewById(R.id.btnBrowser);
        btnBrowser.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://yandex.ru"));
            startActivity(intent);
        });

        // --- Кнопка: Почта ---
        Button btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/*");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@example.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Тестовая тема");
            intent.putExtra(Intent.EXTRA_TEXT, "Привет! Это тестовое письмо.");
            startActivity(Intent.createChooser(intent, "Написать разработчику"));
        });

        // --- Кнопка: Google Play ---
        Button btnMarket = findViewById(R.id.btnMarket);
        btnMarket.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.vkontakte.android"));
                startActivity(intent);
            } catch (Exception e) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.vkontakte.android"));
                startActivity(intent);
            }
        });

        // --- Переключатель: Фонарик ---
        ToggleButton toggleFlash = findViewById(R.id.toggleFlash);
        toggleFlash.setOnCheckedChangeListener((buttonView, isChecked) -> {
            flashlightIsOn = isChecked;
            toggleFlashlight(flashlightIsOn);
            if (flashlightIsOn) {
                Toast.makeText(this, "Фонарик включен", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Фонарик выключен", Toast.LENGTH_SHORT).show();
            }
        });

        // --- Кнопка: Мини-браузер ---
        Button btnWebView = findViewById(R.id.btnWebView);
        btnWebView.setOnClickListener(v ->
                startActivity(new Intent(this, BrowserActivity.class))
        );
    }

    private void toggleFlashlight(boolean turnOn) {
        try {
            if (cameraManager != null && cameraId != null) {
                cameraManager.setTorchMode(cameraId, turnOn);
            }
        } catch (CameraAccessException e) {
            Toast.makeText(this, "Не удалось управлять вспышкой", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Выключаем фонарик при сворачивании
        if (flashlightIsOn) {
            toggleFlashlight(false);
            flashlightIsOn = false;
            ToggleButton toggleFlash = findViewById(R.id.toggleFlash);
            toggleFlash.setChecked(false);
        }
    }
}
