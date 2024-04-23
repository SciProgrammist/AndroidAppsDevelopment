package com.example.ticket_rp191495_ps190756.FirebaseBD

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ticket_rp191495_ps190756.FirebaseBD.datos.Ticket
import com.example.ticket_rp191495_ps190756.R

class AddaptadorTicket(private val context: Activity, var personas: List<Ticket>) :
    ArrayAdapter<Ticket?>(context, R.layout.ticket_layout, personas) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        val layoutInflater = context.layoutInflater
        var rowview: View? = null
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo


        rowview = view ?: layoutInflater.inflate(R.layout.ticket_layout, null)
        val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
        val tvDUI = rowview.findViewById<TextView>(R.id.tvDUI)
        tvNombre.text = "NumeroTicket : " + personas[position].numeroTicket
        tvDUI.text = "Titulo : " + personas[position].titulo
        return rowview
    }
}
