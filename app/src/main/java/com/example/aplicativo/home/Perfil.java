package com.example.aplicativo.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicativo.Login;
import com.example.aplicativo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Perfil extends AppCompatActivity {

    private TextView emailUser, placaVeiculo, nomeUsuario ,dataNascimento , telefoneContato , renavamVeiculo, userCPF;
    private Button deslogar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        IniciarComponentes();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_perfil);

        // Navegação entre o  Menu
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_perfil) {
            } else if (itemId == R.id.bottom_localizacao) {
                startActivity(new Intent(getApplicationContext(), Localizacao.class));
                overridePendingTransition(R.anim.deslizar_direita, R.anim.deslizar_esquerda);
                finish();
            } else if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.deslizar_direita, R.anim.deslizar_esquerda);
                finish();
            }
            return true;
        });

        deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Perfil.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    nomeUsuario.setText(documentSnapshot.getString("nome"));
                    emailUser.setText(email);
                    placaVeiculo.setText(documentSnapshot.getString("placa"));
                    userCPF.setText(documentSnapshot.getString("cpf"));
                    renavamVeiculo.setText(documentSnapshot.getString("renavam"));
                    telefoneContato.setText(documentSnapshot.getString("telefone"));
                    dataNascimento.setText(documentSnapshot.getString("data"));
                }
            }
        });
    }

    private void IniciarComponentes() {
        nomeUsuario = findViewById(R.id.nomeUsuario);
        emailUser = findViewById(R.id.emailUser);
        deslogar = findViewById(R.id.deslogar);
        placaVeiculo = findViewById(R.id.placaVeiculo);
        dataNascimento = findViewById(R.id.dataNascimento);
        renavamVeiculo =  findViewById(R.id.renavamVeiculo);
        userCPF = findViewById(R.id.userCPF);
        telefoneContato = findViewById(R.id.telefoneContato);
    }
}

