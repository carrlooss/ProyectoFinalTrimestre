package com.example.proyectofinaltrimestre

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fragments141124.fragments.FragmentGarnacho
import com.example.fragments141124.fragments.FragmentMenu
import com.example.fragments141124.fragments.FragmentGyokeres
import com.example.fragments141124.fragments.FragmentGavi

class FragmentActiity : AppCompatActivity(), OnFragmentActionListener {
        val bundle=Bundle()
        var boton=0
    val fragments= arrayOf(FragmentGavi(), FragmentGarnacho(), FragmentGyokeres())
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarDatos()
    }
    private fun cargarDatos(){
        val datos=intent.extras
        boton = datos?.getInt("BOTONPULSADO")?:0

        bundle.putInt("BOTONPULSADO", boton)
        val fragmentMenuIluminado=FragmentMenu()
        fragmentMenuIluminado.arguments=bundle
        cargarFragments(fragmentMenuIluminado, fragments[boton])

    }

    private fun cargarFragments(menu: FragmentMenu, fragment: Fragment){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fcv_menu2, menu)
            replace(R.id.fcv_contenido, fragment)
        }
    }

    override fun onClickImagenMenu(btn: Int) {
        bundle.putInt("BOTONPULSADO", btn)
        val fragmentMenuIluminado=FragmentMenu()
        fragmentMenuIluminado.arguments=bundle
        cargarFragments(fragmentMenuIluminado, fragments[btn])
    }
}