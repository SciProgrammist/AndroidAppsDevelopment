package com.example.myapplicationpracticadesafio01reyespineda

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FrmCum : AppCompatActivity() {

    /**
     * Este formulario mostrara la informacion procesada del formulario 01 xD
     *
     */

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frm_cum)

        var txtSalidaNombre = TextView(this)
        var txtSalidaCarnet = TextView(this)
        var txtSalidaCum = TextView(this)
        var txtSalidaUv = TextView(this)
        var btnCumSalida = TextView(this)

        txtSalidaNombre = findViewById(R.id.TxtSalidaNombre)
        txtSalidaCarnet = findViewById(R.id.TxtSalidaCarnet)
        txtSalidaCum = findViewById(R.id.TxtSalidaCum)
        txtSalidaUv = findViewById(R.id.TxtSalidaUv)
        btnCumSalida = findViewById(R.id.BtnSalidaCum)

        val extras = intent.extras

        txtSalidaNombre.text = extras?.getString("nombre")
        txtSalidaCarnet.text = extras?.getString("carnet")
        txtSalidaCum.text = extras?.getString("cum")

        var cum:Float = txtSalidaCum.text.toString().toFloat()
        var uv:Float

        if (cum >= 7.5 ) uv = 32F;
        else if (cum >= 7.0 && cum < 7.5) uv = 24F;
        else if (cum >= 6.0 && cum < 7.0) uv = 20F;
        else uv = 16F;

        txtSalidaUv.text = uv.toString();

        btnCumSalida.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

        fun rationalFromDouble(d: Double): Pair<Int, Int> {
            val s = d.toString()
            val digitsDec = s.length - 1 - s.indexOf('.')

            var tempD = d
            var tempDenom = 1
            repeat(digitsDec) {
                tempD *= 10
                tempDenom *= 10
            }
            val num = tempD.toInt()

            return Pair(num, tempDenom)
        }

   // val d = 3.14
   // val (numerator, denominator) = rationalFromDouble(d)
   // println("Numerator: $numerator, Denominator: $denominator")

}