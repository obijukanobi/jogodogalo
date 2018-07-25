package com.example.miguelreis.jogodogalo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

/**
 * Created by Miguel Reis on 27/01/2016.
 */
public class Basedados {
    private Conexao conexaobd;
    private SQLiteDatabase bd;

    private class Conexao extends SQLiteOpenHelper {

        public Conexao (Context context) {
            super (context, "bdgalo", null, 1);
        }
        @Override
        public  void onCreate (SQLiteDatabase db) {
            db.execSQL("create table jogos (_id integer primary KEY,jogador varchar(50), tresultado varchar(50), nome_imagem varchar(50), tempo integer)");
        }

        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public Basedados (Context contexto_inicial){
        conexaobd = new Conexao(contexto_inicial);
        bd = conexaobd.getWritableDatabase();

    }



    //       char sep = '....';
  //      SimpleDateFormat formato = new SimpleDateFormat("dd" + sep + "MM" + sep +"yyyy");
//
//        File f = new File("......csv");
//        BufferedReader br = new BufferedReader(f);
//
//        String texto = br.readLine();
//
//        String iniciosql = "insert into minha_tabela ";
//        String[] colunas  = texto.split(";");
//        String colunas_texto = "(";
//
//        for (int i= 0;i<colunas.length;++i){
//            colunas_texto += colunas[i] + ",";
//        }
//
//        colunas_texto +=")";
//
//        texto = br.readLine();
//
//        while(texto != null){
//            String[] valores  = texto.split(";");
//
//            String valores_texto ="(";
//            for (int i= 0;i<valores.length;++i){
//                valores_texto += valores[i] + ",";
//            }
//            valores_texto +=")";
//
//            String sql = iniciosql + colunas_texto + " values " + valores_texto; // insert into minha_tabela (col1,col2,col3) values (val1, val2, val3)
//            db.execSql(sql);
//
//            texto = br.readLine();

    public void fechar(){
        conexaobd.close();
    }

    public void consultaEscrita (String consulta) {
        bd.execSQL(consulta);
    }

    public Cursor consultaLeitura (String consulta) {
        return bd.rawQuery(consulta, null);
    }
}



