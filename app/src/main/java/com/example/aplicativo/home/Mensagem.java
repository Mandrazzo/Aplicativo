package com.example.aplicativo.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.aplicativo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Mensagem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_mensagem);


        // Navegação entre o  Menu
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_mensagem) {
            } else if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.deslizar_direita, R.anim.deslizar_esquerda);
                finish();
            } else if (itemId == R.id.bottom_localizacao) {
                startActivity(new Intent(getApplicationContext(), Localizacao.class));
                overridePendingTransition(R.anim.deslizar_direita, R.anim.deslizar_esquerda);
                finish();
            } else if (itemId == R.id.bottom_perfil) {
                startActivity(new Intent(getApplicationContext(), Perfil.class));
                overridePendingTransition(R.anim.deslizar_direita, R.anim.deslizar_esquerda);
                finish();
            }
            return true;

        });
        //Fim
    }
}