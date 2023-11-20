package com.example.aplicativo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Recuperacao extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button btnVoltar;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacao);

        firebaseAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.editTextEmail); // Substitua pelo ID do seu campo de e-mail

        Button recuperarSenhaButton = findViewById(R.id.recuperarSenha); // Substitua pelo ID do seu botão
        btnVoltar = findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recuperacao.this, Login.class);
                startActivity(intent);
            }
        });

        recuperarSenhaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(Recuperacao.this, "Digite seu e-mail", Toast.LENGTH_SHORT).show();
                } else {
                    // Verifique se o e-mail está cadastrado no Firebase
                    firebaseAuth.fetchSignInMethodsForEmail(email)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    if (task.getResult().getSignInMethods().isEmpty()) {
                                        Toast.makeText(Recuperacao.this, "E-mail não cadastrado. Verifique o endereço de e-mail.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // E-mail cadastrado, envie o e-mail de redefinição de senha
                                        firebaseAuth.sendPasswordResetEmail(email)
                                                .addOnCompleteListener(passwordResetTask -> {
                                                    if (passwordResetTask.isSuccessful()) {
                                                        Toast.makeText(Recuperacao.this, "E-mail de redefinição de senha enviado com sucesso. Verifique sua caixa de entrada.", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(Recuperacao.this, "Falha ao enviar o e-mail de redefinição de senha. Tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                } else {
                                    Toast.makeText(Recuperacao.this, "Erro ao verificar o e-mail. Tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}