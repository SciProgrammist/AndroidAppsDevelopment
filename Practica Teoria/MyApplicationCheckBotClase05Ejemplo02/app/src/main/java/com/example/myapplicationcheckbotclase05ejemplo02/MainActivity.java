package com.example.myapplicationcheckbotclase05ejemplo02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBoton01=(Button)findViewById(R.id.btnBoton01);
        btnBoton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtView02 = (TextView)findViewById(R.id.textView02);
                spinner1 = (Spinner) findViewById(R.id.spinner1);

                txtView02.setText("Opcion seleccinada: " + spinner1.getSelectedItem().toString());
                txtView02.refreshDrawableState();
            }
        });
    }
}