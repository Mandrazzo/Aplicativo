package com.example.aplicativo.home;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.content.Intent;
import android.Manifest;
import com.example.aplicativo.R;
import com.example.aplicativo.recyclerView.Adapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//AAAA
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);



        items = new ArrayList<>();
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");
        items.add("PEDIDO");




        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,items);
        recyclerView.setAdapter(adapter);


        // Navegação entre o  Menu
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_home) {
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

        }); // fim menu

    } // FIM ONCREATE

 } // FIM PROGRAMA



