package com.example.aplicativo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aplicativo.home.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

    public class Login extends AppCompatActivity {
        String[] mensagens = {"Preencha todos os campos", "Login efetuado com sucesso!"};
        private EditText emailedt, senhaedt;
        private ProgressBar progressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            IniciarComponentes();

            Button meuBotao = findViewById(R.id.buttonLogin);
            Button souCliente = findViewById(R.id.cliente);
            TextView cad = findViewById(R.id.cadastro);
            TextView rec = findViewById(R.id.esqueciSenha);

            // Click do botão para Logar
            meuBotao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailedt.getText().toString();
                    String senha = senhaedt.getText().toString();

                    if (email.isEmpty() || senha.isEmpty()) {
                        Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.BLACK);
                        snackbar.setTextColor(Color.WHITE);
                        snackbar.show();
                    } else {
                        AutenticarUsuario(v);
                    }
                }
            });

            // Click do botão para registro
            cad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Registro.class);
                    startActivity(intent);
                }
            });
            // Click do botão para recuperação de senha
            rec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Recuperacao.class);
                    startActivity(intent);
                }
            });

            souCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Rastreio.class);
                    startActivity(intent);
                }
            });
        }

        private void AutenticarUsuario(View view) {
            String email = emailedt.getText().toString();
            String senha = senhaedt.getText().toString();

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                               TelaPrincipal();
                            }
                        }, 3000);
                    } else {
                        String erro;
                        try {
                            throw task.getException();
                        } catch (Exception e) {
                            erro = "Erro ao logar usuário";
                        }
                        Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.BLACK);
                        snackbar.setTextColor(Color.WHITE);
                        snackbar.show();
                    }
                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();
            FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

            if(usuarioAtual !=null){
                TelaPrincipal();
            }
        }

        private void TelaPrincipal(){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        private void IniciarComponentes() {
            emailedt = findViewById(R.id.emailedt);
            senhaedt = findViewById(R.id.senhaedt);
            progressBar = findViewById(R.id.progressBar); // Adicionei isso para inicializar a progressBar
        }
    }

