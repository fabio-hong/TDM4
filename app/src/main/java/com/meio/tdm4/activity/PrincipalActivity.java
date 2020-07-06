package com.meio.tdm4.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.meio.tdm4.R;
import com.meio.tdm4.config.ConfiguracaoFirebase;
import com.meio.tdm4.helper.Base64Custom;

// Swipe, RecyclerView, AtualizarSaldo, Recuperar despesaTotal e receitaTota e nome,

public class PrincipalActivity extends AppCompatActivity {

    private TextView textNomeNoInicio;
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.pegarAutenticacaoFirebase();
    private DatabaseReference primeiroNo = ConfiguracaoFirebase.pegarFirebaseDatabase();
    private DatabaseReference referenciaDoNomeDoUsuario;
    private ValueEventListener valueEventListenerUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        textNomeNoInicio = findViewById(R.id.textNomeNoInicio);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mostrarNome();


    }

    @Override
    protected void onStop() {
        super.onStop();
        referenciaDoNomeDoUsuario.removeEventListener(valueEventListenerUsuario);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater é o objeto que vai converter o XML em um view
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuSair:
                autenticacao.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void mostrarNome() {

       String emailUsuario = autenticacao.getCurrentUser().getEmail();
       String idUsuario = Base64Custom.codificarBase64(emailUsuario);
       referenciaDoNomeDoUsuario = primeiroNo.child("usuarios").child(idUsuario).child("nome");
       valueEventListenerUsuario = referenciaDoNomeDoUsuario.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               String nome = dataSnapshot.getValue().toString();
               textNomeNoInicio.setText(nome);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }


    public void jogarComBot(View view){

        //até o clique tá ok

        //testei clicando botão e deu ruim


        startActivity(new Intent(this, BotActivity.class));


    }

}