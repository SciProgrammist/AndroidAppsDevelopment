package com.example.myapplicationradiobutton19febrero2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup01;
    private RadioButton radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup01 = (RadioGroup) findViewById(R.id.radioGroupInformatica);
        radioGroup01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int idSeleccionado) {
                radioButton  = (RadioButton) findViewById(idSeleccionado);

                if(R.id.radioButtonRedes == radioButton.getId());


                if(idRadioButton == R.id)



            }
        });
    }
}