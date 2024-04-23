package com.example.ticket_rp191495_ps190756.FirebaseBD

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.ticket_rp191495_ps190756.FirebaseBD.datos.EstadoTicket
import com.example.ticket_rp191495_ps190756.FirebaseBD.datos.Ticket
import com.example.ticket_rp191495_ps190756.R
import com.example.ticket_rp191495_ps190756.Utils.Utils
import com.google.firebase.database.FirebaseDatabase
import com.example.ticket_rp191495_ps190756.Utils.*
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import java.util.Random

/*  Asi se deberian de llenar los ticket nuevos, y sus datos deben
 *  ser completados con la opcion editar.
 *
 *  val ticket = Ticket(
 *       numeroTicket = 123,
 *       titulo = "Problema con la conexión de red",
 *       descripcion = "No puedo acceder a internet desde mi computadora",
 *      departamentoUsuario = "Tecnología",
 *       autor = "Juan Pérez",
 *       emailAutor = "juan.perez@example.com",
 *       fechaCreacion = Date(),
 *      estado = EstadoTicket.ACTIVO,
 *       fechaFinalizacion = null
 *  )
 */

class AddTicketActivity : AppCompatActivity() {

    private lateinit var edtDescripcionTickets: EditText
    private lateinit var edtTitulo: EditText
    private lateinit var edtDepartamentoUsuario: EditText
    private lateinit var edtAutorTickets: EditText
    private lateinit var edtCorreoTickets: EditText
    private lateinit var edtFechaCreacion: EditText
    private lateinit var edtNumeroTicket: EditText
    private lateinit var edtEstado: EditText
    private lateinit var mAuth: FirebaseAuth

    private var key = ""
    private var accion = ""
    private var  utils = Utils()

    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ticket)
        inicializar()
    }


    private fun inicializar() {
        edtDescripcionTickets = findViewById(R.id.edtDescripcionTicket)
        edtTitulo = findViewById(R.id.edtTituloTicket)
        edtDepartamentoUsuario = findViewById(R.id.edtDepartamentoTicket)
        edtAutorTickets = findViewById(R.id.edtAutorTicket)
        edtCorreoTickets = findViewById(R.id.edtCorreoTicket)
        edtFechaCreacion = findViewById(R.id.edtFechaAlta)
        edtNumeroTicket = findViewById(R.id.edtNumeroTicket)
        edtEstado  = findViewById(R.id.edtEstado)
        utils = Utils()

        // Obtención de datos que envia actividad anterior
        val fechaActual = Date()
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaFormateada = formatoFecha.format(fechaActual)
        val numeroTicketExtra = intent.getStringExtra("numeroTicket")

        intent.extras?.run {
            accion = getString("accion").toString()
            edtFechaCreacion.setText(fechaFormateada)
            edtCorreoTickets.setText(getString("emailAutor"))
            edtAutorTickets.setText(getString("autor"))
            edtTitulo.setText(getString("titulo"))
            edtDepartamentoUsuario.setText(getString("departamentoUsuario"))
            edtDescripcionTickets.setText(getString("descripcion"))
            edtNumeroTicket.setText(
                if (!numeroTicketExtra.isNullOrBlank())
                    numeroTicketExtra
                else
                    utils.generarNumeroTicket()
            )
            key = edtNumeroTicket.text.toString()

            //validaciones de usuario
            mAuth = FirebaseAuth.getInstance()
            // Obtiene el usuario actualmente autenticado
            val user = mAuth.currentUser
            val email = user?.email
            Log.d("AddTicketActivity", "Usuario logueado => $email")

            if(email == "administrador@gmail.com"){
                edtEstado.isEnabled = true
                edtEstado.setText(getString("estado"))
            }else{
                edtEstado.setText( EstadoTicket.ACTIVO.toString())
            }

        }


    }




    fun guardar(v: View?) {
        val titulo = edtTitulo.text.toString()
        val descripcion = edtDescripcionTickets.text.toString()
        val departamentoUsuario = edtDepartamentoUsuario.text.toString()
        val autor = edtAutorTickets.text.toString()
        val emailAutor = edtCorreoTickets.text.toString()
        val fechaCreacion = edtFechaCreacion.text.toString()
        val numeroTicket = edtNumeroTicket.text.toString()
        val estado = edtEstado.text.toString()

        database = FirebaseDatabase.getInstance()

        val ticketRef = database.getReference("tickets").child(key)
        val ticket = Ticket(numeroTicket, numeroTicket, titulo, descripcion, departamentoUsuario, autor, emailAutor, fechaCreacion, estado, "")

        if (accion == "a") {
            ticketRef.setValue(ticket).addOnSuccessListener {
                Toast.makeText(this, "Se guardó con éxito", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        } else {
            val fechaActual = Date()
            val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            var fechaFinalizacion = formatoFecha.format(fechaActual)
            if(estado !== "FINALIZADO"){
                fechaFinalizacion = ""
            }

            val ticketRef = database.getReference("tickets").child(key) // Utilizamos la clave existente para actualizar el ticket existente
            val ticket = Ticket(numeroTicket, numeroTicket, titulo, descripcion, departamentoUsuario, autor, emailAutor, fechaCreacion, estado, fechaFinalizacion)

            ticketRef.setValue(ticket).addOnSuccessListener {
                Toast.makeText(this, "Se actualizó con éxito", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }

    fun cancelar(v: View?) {
        finish()
    }


}