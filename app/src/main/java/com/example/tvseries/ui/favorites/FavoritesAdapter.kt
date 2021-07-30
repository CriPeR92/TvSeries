package com.example.tvseries.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.databinding.ItemShowFavoriteBinding
import com.example.tvseries.model.Show

/**
 * Adapter of the list of tracks
 */

class FavoritesAdapter(private var fragment: FavoritesFragment, var list: ArrayList<Show>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesAdapterViewHolder>() {

    private lateinit var vm: FavoritesViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemShowFavoriteBinding.inflate(layoutInflater)
        vm = ViewModelProvider(fragment).get(FavoritesViewModel::class.java)
        return FavoritesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapterViewHolder, position: Int) {
        holder.binding.viewModel = vm
        holder.binding.show = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FavoritesAdapterViewHolder(val binding: ItemShowFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)
}
