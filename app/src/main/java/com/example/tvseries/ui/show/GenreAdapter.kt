package com.example.tvseries.ui.show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.databinding.ItemGenreBinding
import com.example.tvseries.databinding.ItemShowBinding
import com.example.tvseries.ui.home.HomeViewModel

/**
 * Adapter of the list of tracks
 */

class GenreAdapter(private var fragment: ShowFragment, private var list: ArrayList<String>) :
    RecyclerView.Adapter<GenreAdapter.GenreAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(layoutInflater)
        return GenreAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreAdapterViewHolder, position: Int) {
        holder.binding.genre = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class GenreAdapterViewHolder(val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root)
}