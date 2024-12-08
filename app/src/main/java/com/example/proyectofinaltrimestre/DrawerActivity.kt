package com.example.proyectofinaltrimestre

import android.content.Intent
import android.media.tv.TvContract.Channels.Logo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinaltrimestre.databinding.DrawerLayoutBinding
import com.example.proyectofinaltrimestre.models.PerfilModel
import com.example.proyectofinaltrimestre.providers.db.CrudPerfil

class DrawerActivity : AppCompatActivity() {
    private lateinit var binding: DrawerLayoutBinding // Asegúrate de que esta clase coincida con tu diseño XML
    lateinit var perfil: PerfilModel

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
    }

    private fun irActivityMapa() {
        startActivity(Intent(this, MapaActivity::class.java))
    }

    private fun loadUser(){
        var perfilAux = CrudPerfil().seekByLogin("Carlos")
        if (perfilAux != null) {
            perfil = perfilAux
        }
    }

    private fun irActivityPerfil() {
        loadUser()
        val i=Intent(this, PerfilActivity::class.java).apply {
            putExtra("PERFIL", perfil)
        }
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
}