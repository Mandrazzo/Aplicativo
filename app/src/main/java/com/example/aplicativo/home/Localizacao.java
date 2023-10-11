package com.example.aplicativo.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.aplicativo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Localizacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_localizacao);

        // Navegação entre o  Menu
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_localizacao) {
            } else if (itemId == R.id.bottom_mensagem) {
                startActivity(new Intent(getApplicationContext(), Mensagem.class));
                overridePendingTransition(R.anim.deslizar_direita, R.anim.deslizar_esquerda);
                finish();
            } else if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.deslizar_direita, R.anim.deslizar_esquerda);
                finish();
            } else if (itemId == R.id.bottom_perfil) {
                startActivity(new Intent(getApplicationContext(), Perfil.class));
                overridePendingTransition(R.anim.deslizar_direita, R.anim.deslizar_esquerda);
                finish();
            }
            return true;

        });
            // Fim
    }
}