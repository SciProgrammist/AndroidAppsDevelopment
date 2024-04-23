package com.example.myapplicationpracticadesafio01reyespineda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtNombre = EditText(this)
        var txtCarnet = EditText(this)
        var txtCum = EditText(this)

        txtNombre = findViewById(R.id.TxtNombre)
        txtCarnet = findViewById(R.id.TxtCarnet)
        txtCum = findViewById(R.id.TxtCum)

        var btnCum = Button(this)

        btnCum = findViewById(R.id.BtnCum)

        btnCum.setOnClickListener{
            //El objeto intent tendra algunos de datos importante de la activity que queramos recordar para
            //nuestra nueva activity.
         /*   val intent = Intent(this, FrmCum::class.java)
            intent.putExtra("nombre", txtNombre.text.toString())
            intent.putExtra("carnet", txtCarnet.text.toString())
            intent.putExtra("cum", txtCum.text.toString())
            startActivity(intent) */

            //Aqui va la solucion planteada


             var f1:Float = txtNum1.text.toString().toFloat() / txtDen1.text.toString().toFloat()
             var f2:Float = txtNum2.text.toString().toFloat() / txtDen2.text.toString().toFloat()

            val intent = Intent(this, FrmOperar::class.java)

            intent.putExtra("Suma de f1 + f2: " + Math.round(f1 +f2))
            intent.putExtra("Suma de f1 + f2: " + Math.round(f1-f2))
            intent.putExtra("Suma de f1 + f2: " + Math.round(f1*f2) )
            intent.putExtra("Suma de f1 + f2: " + Math.round(f1/f2))
            startActivity(intent)

        } //Esta es la manera en la que preparamos datos para pasar los datos de una Activity a otra.




    }



}