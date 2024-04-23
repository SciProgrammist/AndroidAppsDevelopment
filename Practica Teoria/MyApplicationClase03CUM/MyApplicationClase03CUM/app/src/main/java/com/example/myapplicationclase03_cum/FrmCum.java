package com.example.myapplicationclase03_cum;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FrmCum extends AppCompatActivity {
    Float cum;
    Integer uv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cum);

        TextView txtSalidaNombre = (TextView) findViewById(R.id.TxtSalidaNombre);
        TextView txtSalidaCarnet = (TextView) findViewById(R.id.TxtSalidaCarnet);
        TextView txtSalidaCum = (TextView) findViewById(R.id.TxtSalidaUv);
        TextView txtSalidaUv = (TextView) findViewById(R.id.TxtSalidaCum);
        final Button btnCumSalida = (Button) findViewById(R.id.BtnSalidaCum);

        Bundle bundle = this.getIntent().getExtras();

        txtSalidaNombre.setText("Nombre: " + bundle.getString("NOMBRE"));
        txtSalidaCarnet.setText("Carnet: " + bundle.getString("CARNET"));
        txtSalidaCum.setText("CUM: " + bundle.getString("CUM"));

        cum = Float.parseFloat(bundle.getString("CUM"));

        if (cum >= 7.5) uv = 32;
        else if (cum >= 7.00 && cum < 7.5) uv = 24;
        else if (cum >= 6.0 && cum < 7.0) uv = 20;
        else uv = 16;

        txtSalidaUv.setText("UV: " + uv.toString());

        btnCumSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrmCum.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}