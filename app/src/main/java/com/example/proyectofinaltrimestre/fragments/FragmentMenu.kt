package com.example.fragments141124.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.proyectofinaltrimestre.OnFragmentActionListener
import com.example.proyectofinaltrimestre.R


class FragmentMenu : Fragment() {
    private  var listener: OnFragmentActionListener? = null
    private val listaImagenesView= arrayOf(R.id.iv_gavi, R.id.iv_garnacho, R.id.iv_gyokeres)
    private val imagenesBotonesIluminados= arrayOf(
        R.drawable.gavi,
        R.drawable.garnacho,
        R.drawable.gyokeres)
    var botonIluminado=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var imageView: ImageView
        if(arguments!=null){
            botonIluminado=requireArguments().getInt("BOTONPULSADO")
        }
        for(i in listaImagenesView.indices){
            imageView=view.findViewById(listaImagenesView[i])
            if(botonIluminado==i){
                imageView.setImageResource(imagenesBotonesIluminados[i])
            }
            //ponemos el listener a todos y cada uno de los images views
            imageView.setOnClickListener {
                listener?.onClickImagenMenu(i)
            }

        }
    }

    override fun onAttach(context: Context) {
        //se llama cuando cargamos el fragment en un activity
        super.onAttach(context)
        if(context is OnFragmentActionListener) listener=context
    }

    override fun onDetach() {
        super.onDetach()
        listener=null
    }


}