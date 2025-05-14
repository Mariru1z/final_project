package com.example.agenda_app_kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aplicarInsets()
        configurarBotones()
    }

    private fun aplicarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configurarBotones() {
        //  Botón para iniciar sesión con Google
        findViewById<Button>(R.id.btnGoogleSignIn).setOnClickListener {
            iniciarSesionConGoogle()
        }

        // Botón para navegar a FragmentTareas (crear evento personalizado)
        findViewById<Button>(R.id.btnCrearEvento).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.contenedorFragmentos, com.example.agenda_app_kotlin.nav_fragment.FragmentInicio())

                .addToBackStack(null)
                .commit()
        }

    }

    private fun iniciarSesionConGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (task.isSuccessful) {
                val account = task.result
                Toast.makeText(this, " Bienvenido, ${account?.displayName}", Toast.LENGTH_SHORT).show()
            } else {
                Log.e("LOGIN", "Error en Google Sign-In", task.exception)
                Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
