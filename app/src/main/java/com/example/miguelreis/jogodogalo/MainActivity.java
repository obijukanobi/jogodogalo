package com.example.miguelreis.jogodogalo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static ImageView[][] tabuleiro_imagens;
    Galo jogo;
    String jog1;
    String jog2;
   // Galo[] jogos;
    //int jogo_corrente;
    Basedados bd;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflates menu
        getMenuInflater().inflate(R.menu.main, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_regras:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegrasActivity.class);
                startActivity(intent);
                return true;

            case R.id.definicoes:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, SettingsActivity.class);
                startActivity(intent2);
                return true;
            case R.id.menu_ultimos_jogos:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, UltimosJogosActivity.class);
                startActivity(intent3);


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializacaoTabuleiroImagens();
        jogo = new Galo();
        jogo.inicializacaoTabuleiro();

        ImageView b = (ImageView) findViewById(R.id.bcomeca);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReinicializacaoTabuleiroImagens();
                jogo.inicializacaoTabuleiro();
                ;
            }
        });

        bd=new Basedados(this);
    }


    public void inicializacaoTabuleiroImagens () {
        tabuleiro_imagens = new android.widget.ImageView[3][3];

        tabuleiro_imagens[0][0] = (ImageView)findViewById(R.id.p11);
        tabuleiro_imagens[0][1] = (ImageView)findViewById(R.id.p12);
        tabuleiro_imagens[0][2] = (ImageView)findViewById(R.id.p13);
        tabuleiro_imagens[1][0] = (ImageView)findViewById(R.id.p21);
        tabuleiro_imagens[1][1] = (ImageView)findViewById(R.id.p22);
        tabuleiro_imagens[1][2] = (ImageView)findViewById(R.id.p23);
        tabuleiro_imagens[2][0] = (ImageView)findViewById(R.id.p31);
        tabuleiro_imagens[2][1] = (ImageView)findViewById(R.id.p32);
        tabuleiro_imagens[2][2] = (ImageView)findViewById(R.id.p33);

        int tam = tabuleiro_imagens[0].length;
        final String empate = getResources() .getString(R.string.empate);
        final String vitoria = getResources() .getString(R.string.vitoria);

        //definir click nas imagens
        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < tam; ++j) {

                final int final_i = i;
                final int final_j = j;

                tabuleiro_imagens[i][j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                 //mudar para bola

                    //tabuleiro_imagens [final_i][final_j].setImageResource(R.drawable.bola);
                    if (jogo.jogoAcabado() == ResultadosGalo.Nada) {
                        jogo.fazerJogada(final_i, final_j);
                        if (jogo.tabuleiro[final_i][final_j] == 'X') {
                            tabuleiro_imagens[final_i][final_j].setImageResource(R.drawable.cruz);
                                                    } else
                            tabuleiro_imagens[final_i][final_j].setImageResource(R.drawable.bola);

                        ResultadosGalo resultado = jogo.jogoAcabado();
                        if (resultado == ResultadosGalo.Empate) {
                            Toast.makeText(getApplicationContext(),  empate, Toast.LENGTH_LONG).show();
                            bd.consultaEscrita("insert into jogos  (jogador, tresultado, nome_imagem, tempo) " +
                                    " values ('" + jog2+ "', ' empata com " + jog1 + "', 'vazio'," + (new Date()).getTime() + ") ");
                        } else if (resultado == ResultadosGalo.VitoriaO) {
                            Toast.makeText(getApplicationContext(), "0" + " - "+ jog2 + " " + vitoria, Toast.LENGTH_LONG).show();
                            bd.consultaEscrita("insert into jogos  (jogador, tresultado, nome_imagem, tempo) " +
                                    " values ('"+jog2+"', ' ganha a " + jog1 +"', 'bola',"  + (new Date()).getTime() + ") ");

                        } else if (resultado == ResultadosGalo.VitoriaX) {
                            Toast.makeText(getApplicationContext(),("X " + " - " + jog1 + " " +vitoria), Toast.LENGTH_LONG).show();
                            bd.consultaEscrita("insert into jogos  (jogador, tresultado, nome_imagem, tempo) " +
                                    " values ('"+ jog1 + "', ' ganha a " + jog2 +"', 'cruz'," + (new Date()).getTime() + ") ");
                        }
                    }
                }
            });
            }
        }
    }


    public void ReinicializacaoTabuleiroImagens (){

        int tam = tabuleiro_imagens[0].length;

        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < tam; ++j) {
                tabuleiro_imagens[i][j].setImageResource(R.drawable.vazio);
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        String simboloinicial = sharedPref.getString("opcao_lista", "X");


        jogo.definirSimboloInicial(simboloinicial.charAt(0));

        if(simboloinicial == "X") {

            jog1 = sharedPref.getString("jogador1", "Tótó 1");
            jog2 = sharedPref.getString("jogador2", "Tótó 2");
            // TextView texto = (TextView)findViewById(R.id.texto_inicial);
        }
        else {
            jog2 = sharedPref.getString("jogador1", "Tótó 1");
            jog1 = sharedPref.getString("jogador2", "Tótó 2");
        }


    }

}







