package com.example.aplicativo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplicativo.home.MainActivity;
import com.example.aplicativo.rastreio.telaRastreio;

public class Rastreio extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastreio);

        Button voltar = findViewById(R.id.voltar);
        Button rastreio = findViewById(R.id.confirmaRastreio);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rastreio.this, Login.class);
                startActivity(intent);
            }
        });

        rastreio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rastreio.this, telaRastreio.class);
                startActivity(intent);
            }
        });
    }
}