package com.example.proyectofinaltrimestre

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinaltrimestre.databinding.ApiLayoutBinding
import com.example.proyectofinaltrimestre.providers.db.MarvelApiClient
import kotlinx.coroutines.runBlocking
import com.example.proyectofinaltrimestre.models.Character
import com.example.recyclersqlite041124.adapters.ApiAdapter

class ApiActivity : AppCompatActivity() {

    private lateinit var binding: ApiLayoutBinding
    var lista = mutableListOf<Character>()
    private lateinit var adapter: ApiAdapter

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Configurar ViewBinding
        binding = ApiLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar el padding para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.api) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setRecycler()
    }

    private fun setRecycler() {
        val layoutManger= LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManger
        traerRegistros()
        adapter= ApiAdapter(lista)
        binding.recyclerView.adapter=adapter
    }

    private fun traerRegistros() {
        // Llaves de Marvel API
        val publicKey = "8484f3c65a891538d1ddeba94da8809b"
        val privateKey = "da16d89512b3e2d64031cb3a6155c82b4273099f"

        val apiClient = MarvelApiClient.create(publicKey, privateKey)

        runBlocking {
            try {
                // Obtener personajes que comienzan con "Spider"
                var response = apiClient.getCharacters(nameStartsWith = "Spider")
                lista = response.data.results.toMutableList()
//                if(lista.size>0){
//                    binding.api.visibility=View.INVISIBLE
//                }else{
//                    binding.api.visibility=View.VISIBLE
//                }

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

}