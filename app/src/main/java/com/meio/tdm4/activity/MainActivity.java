package com.meio.tdm4.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.meio.tdm4.R;
import com.meio.tdm4.config.ConfiguracaoFirebase;

import android.view.View;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro_login)
                .build());

        setButtonBackVisible(false);
        setButtonNextVisible(false);


        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */


    }

    public void Cadastrar(View view) {

        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarSeUsuarioEstaLogado();
    }


    public void verificarSeUsuarioEstaLogado(){

        //Pega instancia da autenticacao
        autenticacao = ConfiguracaoFirebase.pegarAutenticacaoFirebase();

        //autenticacao.signOut();

        if (autenticacao.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }

    }


    public void abrirTelaPrincipal(){

        startActivity(new Intent(this, PrincipalActivity.class));

    }

    public void buttonEntrar(View view) {

        Intent objIntent = new Intent(this, LoginActivity.class);

        startActivity(objIntent);

    }
}