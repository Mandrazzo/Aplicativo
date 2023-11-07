package com.example.aplicativo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registro extends AppCompatActivity {
    private EditText edit_nome, edit_email, edit_senha, edit_renavam, edit_placa, edit_cpf, edit_data, edit_telefone;
    private Button btnCadastrar;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso!"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button voltar = findViewById(R.id.voltar);
        // Click do botão para voltar para tela de login
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
                getSupportActionBar().hide();
                IniciarComponentes();

                btnCadastrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String nome = edit_nome.getText().toString();
                        String email = edit_email.getText().toString();
                        String senha = edit_senha.getText().toString();
                        String placa = edit_placa.getText().toString();
                        String renavam = edit_renavam.getText().toString();
                        String data = edit_data.getText().toString();
                        String cpf = edit_cpf.getText().toString();
                        String telefone = edit_telefone.getText().toString();


                        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || renavam.isEmpty() || placa.isEmpty() || data.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
                            Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();
                        } else {
                            CadastrarUsuario(v);
                        }
                    }


                    private void CadastrarUsuario(View v) {
                        String email = edit_email.getText().toString();
                        String senha = edit_senha.getText().toString();

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                                    snackbar.setBackgroundTint(Color.WHITE);
                                    snackbar.setTextColor(Color.BLACK);
                                    snackbar.show();
                                }
                            }
                        });
                    }
                });
            }

            private void IniciarComponentes() {
                IniciarComponentes();
                edit_nome = findViewById(R.id.edit_nome);
                edit_email = findViewById(R.id.edit_email);
                edit_senha = findViewById(R.id.edit_senha);
                edit_cpf = findViewById(R.id.edit_cpf);
                edit_placa = findViewById(R.id.edit_placa);
                edit_renavam = findViewById(R.id.edit_renavam);
                edit_data = findViewById(R.id.edit_data);
                edit_telefone = findViewById(R.id.edit_telefone);
                btnCadastrar = findViewById(R.id.btnCadastrar);

            }
        });
    }
}



