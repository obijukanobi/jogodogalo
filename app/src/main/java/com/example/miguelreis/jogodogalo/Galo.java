package com.example.miguelreis.jogodogalo;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Miguel Reis on 16/12/2015.
 */
public class Galo {
    char [] [] tabuleiro;
    char simbolocorrente;
    char simboloinicial;
    final int TAMANHO=3;
    int jogadas ;

    public Galo(){
        simboloinicial = 'X';
        simbolocorrente = simboloinicial;
        jogadas = 0;
    }

    public void definirSimboloInicial(char novosimbolo){
        simboloinicial = novosimbolo;
        if (jogadas == 0){
            simbolocorrente = simboloinicial;
        }
    }

    public void inicializacaoTabuleiro () {
        tabuleiro = new char[3][3]; // cria e define o tamanho do array

        jogadas = 0;
        simbolocorrente = simboloinicial;

        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                tabuleiro[linha][coluna] = ' '; //vazio

            }
        }
    }

    public void fazerJogada (int linha, int coluna){

        if (tabuleiro[linha][coluna]==' ') {
            tabuleiro[linha][coluna] = simbolocorrente;
            if (simbolocorrente == 'X') {
                simbolocorrente = 'O';
            } else {
                simbolocorrente = 'X';

            }

            jogadas++;
        }
    }
    public ResultadosGalo jogoAcabado () {


        if (jogadas >= TAMANHO * 2 -1 && jogadas<=TAMANHO*TAMANHO ) {    //verifica  se nº de jogadas possibilita vitoria
                                        //|| jogadas <= TAMANHO * TAMANHO
            int resdian = 0;
            int reslin = 0;
            int rescol = 0;
            int resdia = 0;

            for (int coluna = 0; coluna < TAMANHO; coluna++) {
//Verificar resultado
                //diagonal positiva
                if (tabuleiro[TAMANHO - 1][TAMANHO - 1] == tabuleiro[TAMANHO - 1 - coluna][TAMANHO - 1 - coluna]) {
                    resdia = resdia + 1;
                    if (resdia == TAMANHO) {
                        if (simbolocorrente == 'O') {
                            return ResultadosGalo.VitoriaX;
                        } else {
                            return ResultadosGalo.VitoriaO;
                        }
                    }
                }
//diagonal negativa
                if (tabuleiro[TAMANHO - 1][0] == tabuleiro[TAMANHO - 1 - coluna][coluna]) {
                    resdian = resdian + 1;
                    if (resdian == TAMANHO) {
                        if (simbolocorrente == 'O') {
                            return ResultadosGalo.VitoriaX;
                        } else {
                            return ResultadosGalo.VitoriaO;
                        }
                    }
                }
//linhas
                reslin = 0;
                for (int linha = 0; linha < TAMANHO; linha++) {
                    if (tabuleiro[coluna][TAMANHO - 1] == tabuleiro[coluna][TAMANHO - 1 - linha]) {
                        reslin = reslin + 1;
                        if (reslin == TAMANHO && tabuleiro[coluna][TAMANHO - 1] != ' ') {
                            if (simbolocorrente == 'O') {
                                return ResultadosGalo.VitoriaX;
                            } else {
                                return ResultadosGalo.VitoriaO;
                            }
                        }
                    }
                }
//colunas
                rescol = 0;
                for (int linha = 0; linha < TAMANHO; linha++) {
                if (tabuleiro[TAMANHO - 1][coluna] == tabuleiro[TAMANHO - 1 - linha][coluna]) {
                    rescol = rescol + 1;
                    if (rescol == TAMANHO && tabuleiro[TAMANHO - 1][coluna]!= ' ') {
                        if (simbolocorrente == 'O') {
                            return ResultadosGalo.VitoriaX;
                        } else {
                            return ResultadosGalo.VitoriaO;
                        }
                    }
                }


            }

           }

        }

        if (jogadas >= TAMANHO*TAMANHO){
            return ResultadosGalo.Empate;
        }

        return ResultadosGalo.Nada;
    }


}





