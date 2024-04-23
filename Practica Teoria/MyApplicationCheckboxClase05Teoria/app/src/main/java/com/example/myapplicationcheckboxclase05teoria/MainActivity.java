package com.example.myapplicationcheckboxclase05teoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxLectura;
    private CheckBox checkBoxPasear;
    private CheckBox checkBoxSeriesTV;
    private CheckBox checkBoxCompras;

    private Button botonEnviar;
    private TextView textViewResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxLectura = findViewById(R.id.idLectura);
        checkBoxPasear = findViewById(R.id.idPasear);
        checkBoxSeriesTV = findViewById(R.id.idSeriesTv);
        checkBoxCompras = findViewById(R.id.idCompras);
        textViewResultado = findViewById(R.id.textViewResultdo);
        botonEnviar = findViewById(R.id.buttonEnviar);

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder01 = new StringBuilder();

                stringBuilder01.append(checkBoxLectura.getText().toString() + ": " + checkBoxLectura.isChecked() + "\n");
                stringBuilder01.append(checkBoxPasear.getText().toString() + ": " + checkBoxPasear.isChecked() + "\n");
                stringBuilder01.append(checkBoxSeriesTV.getText().toString() + ": " + checkBoxSeriesTV.isChecked() + "\n");
                stringBuilder01.append(checkBoxCompras.getText().toString() + ": " + checkBoxCompras.isChecked() + "\n");

                textViewResultado.setText(stringBuilder01);
            }
        });
    }
}