package com.example.proyectofinaltrimestre

import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinaltrimestre.databinding.VerImagenLayoutBinding

class VerImagenActivity : AppCompatActivity() {
    private lateinit var binding: VerImagenLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Configurar ViewBinding
        binding = VerImagenLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar el padding para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.verImagen) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtenemos la imagen que nos pasan
        val datos=intent.extras
        var imageUrl: String = ""
        if(datos!=null) {
            imageUrl = datos.getSerializable("IMAGEN") as String
        }

        if (imageUrl != null) {
            val webView = binding.webView

            // Configurar el WebView
            webView.settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
            }

            // Cargar la imagen
            webView.webViewClient = WebViewClient() // Evitar que abra un navegador externo
            webView.loadUrl(imageUrl)
        } else {
            Toast.makeText(this, "URL no válida", Toast.LENGTH_SHORT).show()
        }
    }
}