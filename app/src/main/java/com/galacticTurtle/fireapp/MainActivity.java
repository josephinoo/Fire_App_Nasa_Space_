package com.galacticTurtle.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    private Button btnReportar,btnHistorial,btnVoluntario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReportar= (Button) findViewById(R.id.btn_report) ;
        btnHistorial= (Button) findViewById(R.id.btn_historial) ;
        btnVoluntario= (Button) findViewById(R.id.btn_voluntario);

        btnReportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),pantallaReport.class);
                startActivity(intent);
            }
        });


    }

}
