package com.example.miguelreis.jogodogalo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Miguel Reis on 11/01/2016.
 */
public class RegrasActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regras);



        Button c = (Button) findViewById(R.id.bvoltar);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(RegrasActivity.this, MainActivity.class);
      //          startActivity(intent);
                finish(); //igual ao botao back do Android





                ;
            }
        });
    }





}
