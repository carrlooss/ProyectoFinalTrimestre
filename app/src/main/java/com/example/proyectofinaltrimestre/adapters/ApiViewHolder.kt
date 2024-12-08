package com.example.recyclersqlite041124.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinaltrimestre.databinding.CharacterLayoutBinding
import com.example.proyectofinaltrimestre.models.Character
import com.bumptech.glide.Glide
import com.example.proyectofinaltrimestre.R

class ApiViewHolder(v: View): RecyclerView.ViewHolder(v) {
    val binding  = CharacterLayoutBinding.bind(v)
    fun render(
        c: Character
    ){
        binding.tvName.setText (c.name)
        binding.tvDescription.setText(c.description)

        val imageUrl = c.thumbnail.path.replace("http", "https") + "." + c.thumbnail.extension
        Glide.with(itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.cargando)
            .error(R.drawable.errorr)
            .into(binding.ivImage)
    }

}
