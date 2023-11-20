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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private EditText edit_nome, edit_email, edit_senha, edit_renavam, edit_placa, edit_cpf, edit_data, edit_telefone;
    private Button btnCadastrar;


    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso!"};
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        IniciarComponentes();

        Button voltar = findViewById(R.id.voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Login.class);
               startActivity(intent);

                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.BLACK);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    CadastrarUsuario(v);
                }
            }
        });
    }

    private void SalvarDadosUsuario(){
        String nome = edit_nome.getText().toString();
        String telefone = edit_telefone.getText().toString();
        String data = edit_data.getText().toString();
        String cpf = edit_cpf.getText().toString();
        String renavam = edit_renavam.getText().toString();
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);
        usuarios.put("telefone",telefone);
        usuarios.put("data",data);
        usuarios.put("cpf",cpf);
        usuarios.put("renavam",renavam);
        usuarios.put("email",email);
        usuarios.put("senha",senha);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    private void IniciarComponentes() {
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.editTextEmail);
        edit_senha = findViewById(R.id.edit_senha);
        edit_cpf = findViewById(R.id.edit_cpf);
        edit_placa = findViewById(R.id.edit_placa);
        edit_renavam = findViewById(R.id.edit_renavam);
        edit_data = findViewById(R.id.edit_data);
        edit_telefone = findViewById(R.id.edit_telefone);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    private void CadastrarUsuario(final View v) {
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            SalvarDadosUsuario();
                            Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.BLACK);
                            snackbar.setTextColor(Color.WHITE);
                            snackbar.show();
                        }else{
                            String erro;
                            try {
                                throw task.getException();

                            }catch (FirebaseAuthWeakPasswordException e) {
                                erro = "Digite uma senha com no mínimo 6 caracteres";
                            }catch (FirebaseAuthUserCollisionException e) {
                                erro = "Este e-mail já está em uso.";
                            }catch (FirebaseAuthInvalidCredentialsException e) {
                                erro = "E-mail inválido";
                            }catch (Exception e){
                                erro = "Erro ao cadastrar usuário";
                            }

                            Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.BLACK);
                            snackbar.setTextColor(Color.WHITE);
                            snackbar.show();
                        }
                    }
                });
    }
}



