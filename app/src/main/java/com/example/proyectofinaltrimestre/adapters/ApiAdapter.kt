package com.example.recyclersqlite041124.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinaltrimestre.R
import com.example.proyectofinaltrimestre.models.Character

class ApiAdapter(
    var lista: MutableList<Character>
): RecyclerView.Adapter<ApiViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.character_layout, parent, false)
        return ApiViewHolder(v)
    }

    override fun getItemCount()=lista.size

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        holder.render(lista[position])
    }
}