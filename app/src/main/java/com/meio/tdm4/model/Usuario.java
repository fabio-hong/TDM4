package com.meio.tdm4.model;

import com.google.firebase.database.DatabaseReference;
import com.meio.tdm4.config.ConfiguracaoFirebase;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String idUsuario;



    public void salvarUsuarioNoFirebase(){

        DatabaseReference primeiroNo = ConfiguracaoFirebase.pegarFirebaseDatabase();

        primeiroNo.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
