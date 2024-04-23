package com.example.ticket_rp191495_ps190756

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.ticket_rp191495_ps190756.LogIn.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var buttonLogOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogOut = findViewById<Button>(R.id.btnCerrarSesion)
        buttonLogOut.setOnClickListener{

            FirebaseAuth.getInstance().signOut().also {
                Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_LONG).show()
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }



    // Agregando el menu de signOut para el menu.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_sign_out->{
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}