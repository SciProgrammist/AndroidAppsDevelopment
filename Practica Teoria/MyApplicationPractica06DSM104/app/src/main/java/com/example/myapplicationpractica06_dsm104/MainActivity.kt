package com.example.myapplicationpractica06_dsm104

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickFrame(v: View?)
    {
        val llamar = Intent(this, FrameLayout::class.java)
        startActivity(llamar)
    }

    fun onCLickLinear(v: View?)
    {
        val llamar = Intent(this, LinearLayout::class.java)
        startActivity(llamar)
    }

    fun onCLickRelative(v: View?)
    {
        val llamar = Intent(this, RelativeLayout::class.java)
        startActivity(llamar)
    }

    fun onCLickTable(v: View?)
    {
        val llamar = Intent(this, TableLayout::class.java)
        startActivity(llamar)
    }

    fun onCLickGrid(v: View?)
    {
        val llamar = Intent(this, GridLayout::class.java)
        startActivity(llamar)
    }
}