package com.example.proyectofinaltrimestre

import android.content.Intent
import android.media.tv.TvContract.Channels.Logo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinaltrimestre.databinding.DrawerLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DrawerActivity : AppCompatActivity() {
    private lateinit var binding: DrawerLayoutBinding // Asegúrate de que esta clase coincida con tu diseño XML
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos View Binding
        binding =DrawerLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración de padding para ajustes de ventana
        ViewCompat.setOnApplyWindowInsetsListener(binding.drawerLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.botonPerfil.setOnClickListener {
            irActivityPerfil()
        }
        binding.botonMapa.setOnClickListener {
            irActivityMapa()
        }
        binding.buttonApi.setOnClickListener {
            irActivityApi()
        }
        binding.buttonPreferencias.setOnClickListener {
            irActivityPreferencias()
        }

        binding.botonFragment.setOnClickListener {
            irActivityFragment()
        }

        binding.btnLogout.setOnClickListener{
            logout()
        }
    }

    private fun irActivityMapa() {
        startActivity(Intent(this, MapaActivity::class.java))
    }

    private fun irActivityPerfil() {
        val i=Intent(this, PerfilActivity::class.java).apply {}
        startActivity(i)
    }

    private fun irActivityApi() {
        val i=Intent(this, ApiActivity::class.java).apply {}
        startActivity(i)
    }
    private fun irActivityPreferencias(){
        val i=Intent(this, PreferenciasActivity::class.java).apply {}
        startActivity(i)
    }

    private fun irActivityFragment(){
        val i=Intent(this, FragmentActiity::class.java).apply {}
        startActivity(i)
    }

    fun logout() {
        // Cerrar sesión
        auth.signOut()
        val i=Intent(this, LoginActivity::class.java).apply {}
        startActivity(i)
    }
}