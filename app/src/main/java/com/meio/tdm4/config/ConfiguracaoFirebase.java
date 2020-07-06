package com.meio.tdm4.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;
    private static DatabaseReference primeiroNo;

    //Método que retorne a instancia do firebaseAuth
    public static FirebaseAuth pegarAutenticacaoFirebase() {

        //Se autenticacao não tiver instancia, pegue a instancia
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }


    //Método que retorna a instancia (referencia?) do firebaseDatabase
    public static DatabaseReference pegarFirebaseDatabase() {

        //Se firebase não tiver instancia, pegue a instancia e a referencia
        if (primeiroNo == null) {
            primeiroNo = FirebaseDatabase.getInstance().getReference();
        }

        return primeiroNo;
    }
}
