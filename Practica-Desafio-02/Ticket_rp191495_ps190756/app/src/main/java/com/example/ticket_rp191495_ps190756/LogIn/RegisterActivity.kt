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

class RegisterActivity : AppCompatActivity() {

    // Declaracion de variables a utilizar para este activity:
    private lateinit var auth: FirebaseAuth

    // Referencia a los componentes:
    private lateinit var buttonRegister: Button
    private lateinit var textViewLogin: TextView

    // Escuchador de FireBaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Se inicializa el objeto FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Se inicializa el objeto buttonRegister.
        buttonRegister = findViewById<Button>(R.id.btnRegister)

        //Se le agrega un detector de devolucion de llamada para el evento click
        buttonRegister.setOnClickListener {

            // Se cargan las variables con los elementos en la activity.
            val email = findViewById<EditText>(R.id.txtEmail).text.toString()
            val password = findViewById<EditText>(R.id.txtPass).text.toString()

            // Se manda a llamar a la funcion que se a creado.
            this.register(email, password)
        }

        textViewLogin = findViewById<TextView>(R.id.textViewLogin)
        textViewLogin.setOnClickListener {
            this.goToLogin()
        }

        // Validar si existe un usuario activo
        this.checkUser()
    }

    // Controlando los estados de uso de la Activity

    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener(authStateListener)
    }


    // Metodos complementarios para el uso del SDK de FireBase y otros elementos de la Activity

    private fun register(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            task -> if (task.isSuccessful) {
                val intent = Intent(this, TicketActivity::class.java)
            startActivity(intent)
            finish()
            }
        }.addOnFailureListener{
            exception -> Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun checkUser() {

        // Verificacion del usuario
        authStateListener = FirebaseAuth.AuthStateListener {
            auth -> if (auth.currentUser != null) {
                val intent = Intent(this, TicketActivity::class.java)
            startActivity(intent)
            finish()
            }
        }
    }



    //Se llego al final de la activity.
}