package com.example.aplicativo.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.aplicativo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        // Navegação entre o  Menu

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_home) {
            } else if (itemId == R.id.bottom_mensagem) {
                startActivity(new Intent(getApplicationContext(), Mensagem.class));
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
        // Fim
    }
}