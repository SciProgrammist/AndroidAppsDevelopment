package com.example.firebase.LogIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.firebase.DBfireBase.PersonasActivity
import com.example.firebase.MainActivity
import com.example.firebase.R
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    // Se crea la referencia del objeto FireBaseAuth
    private lateinit var auth: FirebaseAuth

    // Referencia a componentes de nuestro Layout
    private lateinit var  buttonRegistrar: Button
    private lateinit var textViewLogin: TextView

    //Escuchador de FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializamos el objeto FirebaseAuth
        auth = FirebaseAuth.getInstance()

        buttonRegistrar = findViewById<Button> (R.id.btnRegister)
        buttonRegistrar.setOnClickListener {
            val email = findViewById<EditText>(R.id.txtEmail).text.toString()
            //val emial=editTextEmailAddress.text.toString()
            val password = findViewById<EditText>(R.id.txtPass).text.toString()
            this.register(email, password)
        }

        textViewLogin = findViewById<TextView>(R.id.textViewLogin)
        textViewLogin.setOnClickListener {
            this.goToLogin()
        }

        // Validar si existe un usuario activo
        this.checkUser()
    }

    /**
     *  Ahora implementamos los metodos onResume y onPause, en el metodo onResume estamos obteniendo el
     *  estado del objeto FireBaseAuth y en el metodo onPause estamos removiendo la verificacion del estado
     *  del objeto.
     */
    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener(authStateListener)
    }

    /**
     * Nueva funcion que nos permite validar si existe una sesion disponible. En caso que exista
     * se redireccionara a la pantalla ActivityMian.
     *
     */

    private fun checkUser()
    {
        //Verificacion del usuario
        authStateListener = FirebaseAuth.AuthStateListener {  auth ->
            if (auth.currentUser != null ) {
                // Cambiando la vista
                val intent = Intent(this, PersonasActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun register(email: String, password: String)
    {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task -> if (task.isSuccessful)
            {
                val intent = Intent(this, PersonasActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
            exception -> Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


}