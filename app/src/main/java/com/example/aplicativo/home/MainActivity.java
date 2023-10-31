package com.example.aplicativo.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

         /* TENTATIVA DE SOLICITAR PERMISSÃO
        class LocationActivity extends AppCompatActivity {
            private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                // Verifica se a permissão de localização foi concedida
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // A permissão já foi concedida
                    // Você pode acessar a localização aqui
                } else {
                    // Solicita permissão de localização
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                }
            }


           @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // A permissão de localização foi concedida pelo usuário
                        // Agora você pode acessar a localização
                    } else {
                        // A permissão de localização foi negada pelo usuário
                        finish(); */
    }
}
        // Fim
