package com.example.proyectofinaltrimestre

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinaltrimestre.databinding.GaleriaMultimediaBinding

class GaleriaActivity : AppCompatActivity() {

    private lateinit var binding: GaleriaMultimediaBinding // Asegúrate de que esta clase coincida con tu diseño XML
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Inicializamos View Binding
        binding = GaleriaMultimediaBinding.inflate(layoutInflater)
        setContentView(binding.root)




        // Configuración de padding para ajustes de ventana
        ViewCompat.setOnApplyWindowInsetsListener(binding.galeria) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





    }


    }


