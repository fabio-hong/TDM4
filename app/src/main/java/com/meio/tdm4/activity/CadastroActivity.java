package com.meio.tdm4.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.meio.tdm4.R;
import com.meio.tdm4.config.ConfiguracaoFirebase;
import com.meio.tdm4.helper.Base64Custom;
import com.meio.tdm4.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button buttonCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        getSupportActionBar().setTitle("Cadastro");

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {

                            //Instanciar objUsuario
                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);

                            cadastrarUsuario();

                        } else {
                            Toast.makeText(CadastroActivity.this, R.string.toast_senha, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, R.string.toast_email, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, R.string.toast_nome, Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    public void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.pegarAutenticacaoFirebase();

        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    //Codificar email do usuario e settar como seu id
                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);

                    //Salvar como idUsuario seu email codificado
                    usuario.salvarUsuarioNoFirebase();

                    //Fechar cadastro activity
                    finish();


                } else {

                    String excecao = "";

                    try {

                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {

                        excecao = getString(R.string.toast_senhaForte);

                    } catch (FirebaseAuthInvalidCredentialsException e) {

                        excecao = getString(R.string.toast_senhaInvalida);

                    } catch (FirebaseAuthUserCollisionException e) {

                        excecao = getString(R.string.toast_contaExiste);

                    } catch (Exception e) {

                        excecao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}