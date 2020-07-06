package com.meio.tdm4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.meio.tdm4.AtributosDesseSaque;
import com.meio.tdm4.R;

import org.w3c.dom.Text;

import java.util.Random;

public class BotActivity extends AppCompatActivity {

    private Button buttonSePosicionarNaDireita, buttonSePosicionarNaEsquerda,
            buttonSacarParaDireita, buttonSacarParaEsquerda,
            buttonSacarComEfeitoMorto, buttonSacarComEfeitoCozinhado,
            buttonSacar;

    private TextView textSaqueOuMeioDoJogo, textPosicao, textDirecao, textEfeito,
            textDirecaoBot, textEfeitoBot, textX;

    private TextView textPlacarUsuario, textPlacarBot,textNomeBot, textNomeUsuario;

    private String posicaoUsuario;
    private String direcaoUsuario;
    private String efeitoUsuario;

    private String posicaoBot;
    private String direcaoBot;
    private String efeitoBot;

    private String acertouOuErrou;

    private String saqueOuJogo;

    private int placarBot;
    private int placarUsuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);

        buttonSePosicionarNaDireita = findViewById(R.id.buttonSePosicionarNaDireita);
        buttonSePosicionarNaEsquerda = findViewById(R.id.buttonSePosicionarNaEsquerda);
        buttonSacarParaDireita = findViewById(R.id.buttonSacarParaDireita);
        buttonSacarParaEsquerda = findViewById(R.id.buttonSacarParaEsquerda);
        buttonSacarComEfeitoMorto = findViewById(R.id.buttonSacarComEfeitoMorto);
        buttonSacarComEfeitoCozinhado = findViewById(R.id.buttonSacarComEfeitoCozinhado);
        buttonSacar = findViewById(R.id.buttonSacar);

        textSaqueOuMeioDoJogo = findViewById(R.id.textSaqueOuMeioDoJogo);
        textPosicao = findViewById(R.id.textPosicao);
        textDirecao = findViewById(R.id.textDirecao);
        textEfeito = findViewById(R.id.textEfeito);


        textEfeitoBot = findViewById(R.id.textEfeitoBot);
        textDirecaoBot = findViewById(R.id.textDirecaoBot);
        textEfeitoBot.setVisibility(View.INVISIBLE);
        textDirecaoBot.setVisibility(View.INVISIBLE);

        saqueOuJogo = "saque"; //TODO: nao pode estar em onCreate

        placarBot = 0;
        placarUsuario = 0;

        textPlacarUsuario = findViewById(R.id.textPlacarUsuario);
        textPlacarBot = findViewById(R.id.textPlacarBot);
        textNomeBot = findViewById(R.id.textNomeBot);
        textNomeUsuario = findViewById(R.id.textNomeUsuario);
        textX = findViewById(R.id.textX);


    }


    public void sePosicionarNaDireita(View view){

        posicaoUsuario= "direita";
        definirPosicaoDoUsuario();

    }

    public void sePosicionarNaEsquerda(View view){

        posicaoUsuario = "esquerda";
        definirPosicaoDoUsuario();

    }

    public void definirPosicaoDoUsuario(){

        if (posicaoUsuario.equals("direita")){

            buttonSePosicionarNaDireita.setBackgroundColor(getResources().getColor(R.color.verde));
            buttonSePosicionarNaEsquerda.setBackgroundColor(getResources().getColor(R.color.cinza));

        }else if(posicaoUsuario.equals("esquerda")){

            buttonSePosicionarNaEsquerda.setBackgroundColor(getResources().getColor(R.color.verde));
            buttonSePosicionarNaDireita.setBackgroundColor(getResources().getColor(R.color.cinza));

        }




    }

    public void sacarOuBaterParaDireita(View view){

        direcaoUsuario = "direita";
        definirDirecaoDoSaqueOuBatida();

    }

    public void sacarOuBaterParaEsquerda(View view){

        direcaoUsuario = "esquerda";
        definirDirecaoDoSaqueOuBatida();

    }

    public void definirDirecaoDoSaqueOuBatida(){

        if (direcaoUsuario.equals("direita")){

            buttonSacarParaDireita.setBackgroundColor(getResources().getColor(R.color.verde));
            buttonSacarParaEsquerda.setBackgroundColor(getResources().getColor(R.color.cinza));

        }else if(direcaoUsuario.equals("esquerda")){

            buttonSacarParaEsquerda.setBackgroundColor(getResources().getColor(R.color.verde));
            buttonSacarParaDireita.setBackgroundColor(getResources().getColor(R.color.cinza));

        }

    }

    public void sacarOuBaterEfeitoMorto(View view){

        efeitoUsuario = "morto";
        definirEfeitoDoUsuario();


    }

    public void sacarOuBaterComEfeitoCozinhado(View view){

        efeitoUsuario = "cozinhado";
        definirEfeitoDoUsuario();

    }

    public void definirEfeitoDoUsuario(){

        if (efeitoUsuario.equals("morto")){

            buttonSacarComEfeitoMorto.setBackgroundColor(getResources().getColor(R.color.verde));
            buttonSacarComEfeitoCozinhado.setBackgroundColor(getResources().getColor(R.color.cinza));

        }else if(efeitoUsuario.equals("cozinhado")){

            buttonSacarComEfeitoCozinhado.setBackgroundColor(getResources().getColor(R.color.verde));
            buttonSacarComEfeitoMorto.setBackgroundColor(getResources().getColor(R.color.cinza));

        }
    }













    public void sacarOuBater (View view){

        if (saqueOuJogo.equals("saque")){

            Toast.makeText(this, "ainda é saque", Toast.LENGTH_SHORT).show();

            mudarTextoSaqueParaJogo();

            definirRespostaDoBot();

        } else if (saqueOuJogo.equals("jogo")){

            //checarSeUsuarioErrouNoJogo();

            if (checarSeUsuarioErrouNoJogo().equals("acertou")){

                //Usuario acertou - ok
                Toast.makeText(this, "acertou", Toast.LENGTH_SHORT).show();

                definirRespostaDoBot();

            } else if (checarSeUsuarioErrouNoJogo().equals("errou")){

                //Usuario errou - ok
                Toast.makeText(this, "errou", Toast.LENGTH_SHORT).show();

                darPontoParaBot();
                mudarTextoJogoParaSaque();


            } else {

                Toast.makeText(this, "deu ruim", Toast.LENGTH_SHORT).show();

            }

        }
    }

    public void mudarTextoSaqueParaJogo(){

        textSaqueOuMeioDoJogo.setText("JOGO");

        saqueOuJogo = "jogo";

    }

    public void mudarTextoJogoParaSaque(){

        textSaqueOuMeioDoJogo.setText("SAQUE");

        saqueOuJogo = "saque";

    }

    public void definirRespostaDoBot () {

        if (direcaoUsuario.equals("esquerda")) {

            posicaoBot = "direita";

        } else if (direcaoUsuario.equals("direita")){

            posicaoBot = "esquerda";

        }

        if (efeitoUsuario.equals("morto")){

            efeitoBot = "morto";
            textEfeitoBot.setText(efeitoBot);

        }else if (efeitoUsuario.equals("cozinhado")){

            efeitoBot = "cozinhado";
            textEfeitoBot.setText(efeitoBot);

        }


        int umOuDois = new Random().nextInt(2);

        if (umOuDois == 1) {

            direcaoBot = "esquerda";
            textDirecaoBot.setText(direcaoBot);

        } else if (umOuDois == 0){

            direcaoBot = "direita";
            textDirecaoBot.setText(direcaoBot);

        }


        esconderTudo();

    }

    public String checarSeUsuarioErrouNoJogo(){

        if (direcaoBot.equals("esquerda") && posicaoUsuario.equals("esquerda")){

            if (efeitoBot.equals("morto") && efeitoUsuario.equals("morto")){

                acertouOuErrou = "acertou";

            } else if (efeitoBot.equals("cozinhado") && efeitoUsuario.equals("cozinhado")){

                acertouOuErrou = "acertou";

            } else{

                acertouOuErrou = "errou";

            }

        } else if (direcaoBot.equals("direita") && posicaoUsuario.equals("direita")){

            if (efeitoBot.equals("morto") && efeitoUsuario.equals("morto")){

                acertouOuErrou = "acertou";

            } else if (efeitoBot.equals("cozinhado") && efeitoUsuario.equals("cozinhado")){

                acertouOuErrou = "acertou";

            } else{

                acertouOuErrou = "errou";

            }

        } else{

            acertouOuErrou = "errou";

        }

        return acertouOuErrou;
    }


    public void darPontoParaBot(){

        placarBot = placarBot + 1;

        String placarBotEmString = String.valueOf(placarBot);
        textPlacarBot.setText(placarBotEmString);

    }

    public void darPontoParaUsuario(){

        placarUsuario = placarUsuario + 1;

        String placarUsuarioEmString = String.valueOf(placarUsuario);
        textPlacarUsuario.setText(placarUsuarioEmString);
    }





    public void esconderTudo(){

        buttonSePosicionarNaDireita.setVisibility(View.INVISIBLE);
        buttonSePosicionarNaEsquerda.setVisibility(View.INVISIBLE);
        buttonSacarParaDireita.setVisibility(View.INVISIBLE);
        buttonSacarParaEsquerda.setVisibility(View.INVISIBLE);
        buttonSacarComEfeitoMorto.setVisibility(View.INVISIBLE);
        buttonSacarComEfeitoCozinhado.setVisibility(View.INVISIBLE);
        buttonSacar.setVisibility(View.INVISIBLE);
        textSaqueOuMeioDoJogo.setVisibility(View.INVISIBLE);
        textPosicao.setVisibility(View.INVISIBLE);
        textDirecao.setVisibility(View.INVISIBLE);
        textEfeito.setVisibility(View.INVISIBLE);
        textPlacarUsuario.setVisibility(View.INVISIBLE);
        textPlacarBot.setVisibility(View.INVISIBLE);
        textNomeUsuario.setVisibility(View.INVISIBLE);
        textNomeBot.setVisibility(View.INVISIBLE);
        textX.setVisibility(View.INVISIBLE);

        mostrarRespostaDoBot();

    }

    public void mostrarRespostaDoBot(){

        textEfeitoBot.setVisibility(View.VISIBLE);
        textDirecaoBot.setVisibility(View.VISIBLE);

        esconderRespostaDoBot();

        mostrarTudo();

    }

    public void esconderRespostaDoBot(){

        textEfeitoBot.postDelayed(new Runnable(){
            @Override
            public void run() {
                textEfeitoBot.setVisibility(View.GONE);
            }
        }, 3000);


        textDirecaoBot.postDelayed(new Runnable(){
            @Override
            public void run() {
                textDirecaoBot.setVisibility(View.GONE);
            }
        }, 3000);

    }

    public void mostrarTudo(){

        buttonSePosicionarNaDireita.postDelayed(new Runnable(){
            @Override
            public void run() {
                buttonSePosicionarNaDireita.setVisibility(View.VISIBLE);
            }
        }, 3000);

        buttonSePosicionarNaEsquerda.postDelayed(new Runnable(){
            @Override
            public void run() {
                buttonSePosicionarNaEsquerda.setVisibility(View.VISIBLE);
            }
        }, 3000);

        buttonSacarParaDireita.postDelayed(new Runnable(){
            @Override
            public void run() {
                buttonSacarParaDireita.setVisibility(View.VISIBLE);
            }
        }, 3000);

        buttonSacarParaEsquerda.postDelayed(new Runnable(){
            @Override
            public void run() {
                buttonSacarParaEsquerda.setVisibility(View.VISIBLE);
            }
        }, 3000);

        buttonSacarComEfeitoMorto.postDelayed(new Runnable(){
            @Override
            public void run() {
                buttonSacarComEfeitoMorto.setVisibility(View.VISIBLE);
            }
        }, 3000);

        buttonSacarComEfeitoCozinhado.postDelayed(new Runnable(){
            @Override
            public void run() {
                buttonSacarComEfeitoCozinhado.setVisibility(View.VISIBLE);
            }
        }, 3000);

        buttonSacar.postDelayed(new Runnable(){
            @Override
            public void run() {
                buttonSacar.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textSaqueOuMeioDoJogo.postDelayed(new Runnable(){
            @Override
            public void run() {
                textSaqueOuMeioDoJogo.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textPosicao.postDelayed(new Runnable(){
            @Override
            public void run() {
                textPosicao.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textDirecao.postDelayed(new Runnable(){
            @Override
            public void run() {
                textDirecao.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textEfeito.postDelayed(new Runnable(){
            @Override
            public void run() {
                textEfeito.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textPlacarUsuario.postDelayed(new Runnable(){
            @Override
            public void run() {
                textPlacarUsuario.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textPlacarBot.postDelayed(new Runnable(){
            @Override
            public void run() {
                textPlacarBot.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textNomeUsuario.postDelayed(new Runnable(){
            @Override
            public void run() {
                textNomeUsuario.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textNomeBot.postDelayed(new Runnable(){
            @Override
            public void run() {
                textNomeBot.setVisibility(View.VISIBLE);
            }
        }, 3000);

        textX.postDelayed(new Runnable(){
            @Override
            public void run() {
                textX.setVisibility(View.VISIBLE);
            }
        }, 3000);

    }

}