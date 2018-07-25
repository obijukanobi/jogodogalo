package com.example.miguelreis.jogodogalo;

/**
 * Created by Miguel Reis on 20/01/2016.
 */

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.definicoes);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        final Resources res = getResources();
        final EditTextPreference jog1 = (EditTextPreference)
                findPreference("jogador1");
        final EditTextPreference jog2 = (EditTextPreference)
                findPreference("jogador2");

        jog1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String novo_valor = (String) newValue;
               // String textomostrar = res.getString(R.string.mostrar);
                //String textocomosaudacao = res.getString(R.string.comosaudacao);
                jog1.setSummary(novo_valor);
                return true;
            }
        });
        jog2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String novo_valor = (String) newValue;
                // String textomostrar = res.getString(R.string.mostrar);
                //String textocomosaudacao = res.getString(R.string.comosaudacao);
                jog2.setSummary(novo_valor);
                return true;
            }
        });
        //String saudacaocorrente = sharedPref.getString("saudacao", null);

        //if (saudacaocorrente != null) {
        //    definicao_saudacao.setSummary(res.getString(R.string.mostrar)+
        //           " \"" + saudacaocorrente + "\" " + res.getString(R.string.comosaudacao));



        }
//        final ListPreference definicao_lista = (ListPreference)findPreference("opcao_lista");
//
//        definicao_lista.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){
//
//            @Override
//            public boolean onPreferenceChange (Preference preference, Object newValue) {
//                definicao_lista.setSummary((String) newValue);
//                return true;
//            }
//        });

//        String valorlista = sharedPref.getString("opca_lista", null);

//        if (valorlista !=null) {
//            if (valorlista !=null){
//                definicao_lista.setSummary(valorlista);
//            }
//        }
//    }


}