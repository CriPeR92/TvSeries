package com.example.tvseries.ui.show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.databinding.ItemSeasonBinding
import com.example.tvseries.model.Episode
import com.example.tvseries.ui.home.HomeFragment
import com.example.tvseries.ui.home.HomeViewModel

/**
 * Adapter of the list of tracks
 */

class SeasonsAdapter(private var fragment: ShowFragment, var list: ArrayList<Episode>) :
    RecyclerView.Adapter<SeasonsAdapter.SeasonsAdapterViewHolder>() {

    private lateinit var viewModel: ShowViewModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSeasonBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(fragment).get(ShowViewModel::class.java)
        return SeasonsAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonsAdapterViewHolder, position: Int) {
        holder.binding.viewModel = viewModel
        holder.binding.episode = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class SeasonsAdapterViewHolder(val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root)
}