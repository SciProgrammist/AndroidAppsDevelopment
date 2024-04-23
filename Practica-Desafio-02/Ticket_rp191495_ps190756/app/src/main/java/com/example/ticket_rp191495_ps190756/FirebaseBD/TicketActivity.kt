package com.example.ticket_rp191495_ps190756.FirebaseBD


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ticket_rp191495_ps190756.FirebaseBD.datos.Ticket
import com.example.ticket_rp191495_ps190756.LogIn.RegisterActivity
import com.example.ticket_rp191495_ps190756.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener


class TicketActivity : AppCompatActivity() {

    private lateinit var buttonLogOut: Button
    var consultaOrdenada: Query = refTickets.orderByChild("numeroTicket")
    var listaTicket: ListView? = null
    var tickets: MutableList<Ticket>? = null
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        inicializar()

        buttonLogOut = findViewById<Button>(R.id.btnCerrarSesion)
        buttonLogOut.setOnClickListener {

            FirebaseAuth.getInstance().signOut().also {
                Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_LONG).show()
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


    private fun inicializar() {
        val fab_agregar: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab_agregar)
        listaTicket = findViewById<ListView>(R.id.ListaTickets)

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaTicket!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val intent = Intent(getBaseContext(), AddTicketActivity::class.java)
                intent.putExtra("accion", "e") // Editar
                intent.putExtra("key", tickets!![i].key)
                intent.putExtra("numeroTicket", tickets!![i].numeroTicket)
                intent.putExtra("titulo", tickets!![i].titulo)
                intent.putExtra("emailAutor", tickets!![i].emailAutor)
                intent.putExtra("descripcion", tickets!![i].descripcion)
                intent.putExtra("departamentoUsuario", tickets!![i].departamentoUsuario)
                intent.putExtra("autor", tickets!![i].autor)
                intent.putExtra("fechaCreacion", tickets!![i].fechaCreacion)
                intent.putExtra("estado", tickets!![i].estado)
                intent.putExtra("fechaFinalizacion", tickets!![i].fechaFinalizacion)
                //intent.putExtra("tic", tickets!![i].tic)
                startActivity(intent)
            }

        })

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaTicket!!.onItemLongClickListener = object : AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long
            ): Boolean {
                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                val ad = AlertDialog.Builder(this@TicketActivity)
                ad.setMessage("Está seguro de eliminar registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton(
                    "Si"
                ) { dialog, id ->
                    tickets!![position].numeroTicket?.let {
                        refTickets.child(it).removeValue()
                    }
                    Toast.makeText(
                        this@TicketActivity,
                        "Registro borrado!", Toast.LENGTH_SHORT
                    ).show()
                }
                ad.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        Toast.makeText(
                            this@TicketActivity,
                            "Operación de borrado cancelada!", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                ad.show()
                return true
            }
        }
        fab_agregar.setOnClickListener(View.OnClickListener { // Cuando el usuario quiere agregar un nuevo registro
            val i = Intent(getBaseContext(), AddTicketActivity::class.java)
            i.putExtra("accion", "a") // Agregar
            i.putExtra("key", "")
            i.putExtra("nombre", "")
            i.putExtra("dui", "")
            startActivity(i)
        })
        tickets = ArrayList<Ticket>()

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas

                mAuth = FirebaseAuth.getInstance()
                // Obtiene el usuario actualmente autenticado
                val user = mAuth.currentUser
                val email = user?.email
                tickets!!.removeAll(tickets!!)


                Log.d("TicketActivity", "Usuario logueado => $email")

                if (user != null) {
                    for (dato in dataSnapshot.getChildren()) {
                        val ticket: Ticket? = dato.getValue(Ticket::class.java)
                        ticket?.key(dato.key)
                        if (ticket != null) {
                            if (ticket.emailAutor == "carlossalazar2228@gmail.com") {
                                tickets!!.add(ticket)
                            }else if(email != null && email == "administrador@gmail.com"){
                                tickets!!.add(ticket)
                            }
                        }
                    }
                } else {
                    // El usuario no está autenticado, puedes redirigirlo al inicio de sesión
                    // o manejarlo de alguna otra manera
                    Log.d("MainActivity", "Debes iniciar sesión primero")
                }

                val adapter = AddaptadorTicket(
                    this@TicketActivity,
                    tickets as ArrayList<Ticket>
                )
                listaTicket!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    companion object {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refTickets: DatabaseReference = database.getReference("tickets")
    }

}