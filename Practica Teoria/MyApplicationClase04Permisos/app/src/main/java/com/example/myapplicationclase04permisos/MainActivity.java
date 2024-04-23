package com.example.myapplicationclase04permisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //el codigo de solicitu puede ser cualquer valor entero y se utilizara para identificcar que solicitud
    // ha lanzado la llamada a onResquesPermissionResult()
    private static final int RECORD_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solicitarPermiso();
    }

    private void solicitarPermiso() {
        // se procede a verificar si el permiso respectivo ha sido concedido previamente.
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            //Devuelve verdadero si el usuario ha denagado previamente el permiso respectivo o devuelve
            //falso si la solicitud no ha sido realizado previamente
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this, "Permiso para acceder al microfono es requerido para esta apalicaccion"
                , Toast.LENGTH_LONG).show();

                //Proporcionar al usuario la opcion de seguir aceptando los permisos
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        RECORD_REQUEST_CODE);
            } else {
                // mostrar dialogo para consultar permiso para grabar audio
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        RECORD_REQUEST_CODE);
            }

        }

        // si el permiso es concedido, procede a grabar audio
        else if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RECORD_REQUEST_CODE: {
                if (grantResults.length > 0
                       && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Llamada de la funcion para grabar audio
                    // grabarAudio(); llamada dle meodo para grabar audio
                    Toast.makeText(this,"Permiso concedido para grabar audio", Toast.LENGTH_LONG).show();
                } else  {
                    Toast.makeText(this, "Permiso denagado para grabr audio", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}