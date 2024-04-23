package com.example.ticket_rp191495_ps190756.LogIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.ticket_rp191495_ps190756.FirebaseBD.TicketActivity
import com.example.ticket_rp191495_ps190756.MainActivity
import com.example.ticket_rp191495_ps190756.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {


    // Se declara la referencia de la clase FireBaseAuth
    private lateinit var auth: FirebaseAuth

    // Declaracion de los componentes que se estan usando en el Layout
    private lateinit var btnLogin: Button
    private lateinit var textViewRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializamos el objeto FireBaseAuth
        auth = FirebaseAuth.getInstance()

        // Incializacion el boton LogIn
        btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            this.login(email,password)
        }

        // Condigo para que un botoncito llame un metodo:

        textViewRegister = findViewById<TextView>(R.id.textViewRegister)
        textViewRegister.setOnClickListener {
            this.goToRegister()
        }
    }

    // Metodos complementarios para el uso del SDK de FireBase y otros elementos de la Activity

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            task -> if (task.isSuccessful) {
                val intent = Intent(this, TicketActivity::class.java)
            startActivity(intent)
            finish()
            }
        }.addOnFailureListener {
            exception -> Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    //Se llego al final de la activity
}