package com.meio.tdm4.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.meio.tdm4.R;
import com.meio.tdm4.config.ConfiguracaoFirebase;
import com.meio.tdm4.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoSenha, campoEmail;
    private Button buttonEntrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        buttonEntrar = findViewById(R.id.buttonEntrar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {

                        usuario = new Usuario();
                        usuario.setSenha(textoSenha);
                        usuario.setEmail(textoEmail);
                        validarLogin();

                    } else {
                        Toast.makeText(LoginActivity.this, R.string.toast_senha, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, R.string.toast_email, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void validarLogin(){

        //Recuperar instancia de autenticacao do firebase
        autenticacao = ConfiguracaoFirebase.pegarAutenticacaoFirebase();

        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    abrirTelaPrincipal();

                } else {

                    String excecao = "";

                    try {

                        throw task.getException();

                    } catch (FirebaseAuthInvalidUserException e) {

                        excecao = getString(R.string.toast_usuarioInvalido);

                        Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_LONG).show();

                    } catch (FirebaseAuthInvalidCredentialsException e) {

                        excecao = getString(R.string.toast_emailSenhaIncorretos);

                        Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {

                        excecao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    public void abrirTelaPrincipal(){

        startActivity(new Intent(this, PrincipalActivity.class));
        finish();//fecha activity login

    }

    public void buttonEntrar(View view) {

        Intent objIntent = new Intent(this, LoginActivity.class);

        startActivity(objIntent);
    }
}