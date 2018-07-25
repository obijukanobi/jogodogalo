package com.example.miguelreis.jogodogalo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Miguel Reis on 03/02/2016.
 */
public class DetalhesJogosActivity extends  Activity {
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detalhe_jogo);

        Intent intent = getIntent();

        String resultado = intent.getStringExtra("resultado");
        TextView res = (TextView)findViewById(R.id.resultado);
        res.setText(resultado);

        int num_jogo = intent.getIntExtra("jogo", 0);
        TextView textojogo = (TextView)findViewById(R.id.numjogo);
        textojogo.setText("Jogo " + num_jogo);

        String texto_data = intent.getStringExtra("data");
        TextView data =(TextView)findViewById(R.id.data);
        data.setText("Jogo realizado em " + texto_data);

        String texto_imagem = intent.getStringExtra("imagem");
        ImageView img = (ImageView)findViewById(R.id.img_resultado);


        if (texto_imagem.equals("bola")){
            img.setImageResource(R.drawable.bola);
        }
        else if (texto_imagem.equals("cruz")) {
            img.setImageResource(R.drawable.cruz);
        }
        else {
            img.setImageResource(R.drawable.vazio);
        }


    }

}
