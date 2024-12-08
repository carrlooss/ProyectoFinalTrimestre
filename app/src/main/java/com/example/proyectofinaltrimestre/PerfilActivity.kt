package com.example.proyectofinaltrimestre

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinaltrimestre.databinding.PerfilLayoutBinding
import com.example.proyectofinaltrimestre.models.PerfilModel
import com.example.proyectofinaltrimestre.providers.db.CrudPerfil

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: PerfilLayoutBinding // Asegúrate de que esta clase coincida con tu diseño XML

    private var id=-1
    private var nombre=""
    private var apellidos=""
    private var email=""
    private var login=""
    private var password=""
    private var isUpdate=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos View Binding
        binding = PerfilLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración de padding para ajustes de ventana
        ViewCompat.setOnApplyWindowInsetsListener(binding.perfil) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recogerPerfil()
        setListeners()
    }

    private fun recogerPerfil() {
        val datos=intent.extras
        if(datos!=null){
            val c= datos.getSerializable("PERFIL") as PerfilModel
            isUpdate=true
            nombre=c.nombre
            apellidos=c.apellidos
            email=c.email
            login=c.login
            id=c.id

            pintarDatos()
        }
    }

    private fun pintarDatos() {
        binding.etLogin.setText(login)
        binding.etNombre.setText(nombre)
        binding.etApellidos.setText(apellidos)
        binding.etEmail.setText(email)
        binding.etClave.setText(password)
    }

    private fun setListeners() {
        binding.btnCancelar.setOnClickListener {
            finish()
        }
        binding.btn2Guardar.setOnClickListener {
            guardarRegistro()
        }
    }
    private fun guardarRegistro() {
        if(datosCorrectos()){
            val c=PerfilModel(
                id,
                nombre,
                apellidos,
                email,
                login,
                password
            )
            if(!isUpdate) {
                if (CrudPerfil().create(c) != -1L) {
                    Toast.makeText(
                        this,
                        "Se ha añadido un registro a la agenda",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    binding.etEmail.error = "Email duplicado!!!!"
                }
            }else{
                if(CrudPerfil().update(c)){
                    Toast.makeText(this, "Registro Editado", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    binding.etEmail.error = "Email duplicado!!!!"
                }
            }
        }
    }

    private fun datosCorrectos(): Boolean {
        nombre=binding.etNombre.text.toString().trim()
        apellidos=binding.etApellidos.text.toString().trim()
        email=binding.etEmail.text.toString().trim()
        password=binding.etClave.text.toString().trim()
        if(nombre.length<3){
            binding.etNombre.error="El campo nombre debe tener al menos 3 caracteres"
            return false;
        }
        if(apellidos.length<5){
            binding.etApellidos.error="El campo apellidos debe tener al menos 5 caracteres"
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error="Debes introducir un email válido"
            return false;
        }
        if(password.length<5){
            binding.etClave.error="El campo contraseña debe tener al menos 5 caracteres"
            return false;
        }
        return true
    }
}