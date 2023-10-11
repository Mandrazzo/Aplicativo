package com.example.aplicativo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplicativo.home.MainActivity;

public class Recuperacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacao);

        Button voltar = findViewById(R.id.button);


        // Click do botão para voltar para tela de login
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recuperacao.this, Login.class);
                startActivity(intent);
            }
        });

    }
}