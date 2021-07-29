package com.example.tvseries.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.databinding.ItemShowBinding
import com.example.tvseries.model.ShowList

/**
 * Adapter of the list of tracks
 */

class HomeAdapter(private var fragment: HomeFragment, var list: ArrayList<ShowList>) :
    RecyclerView.Adapter<HomeAdapter.TracksAdapterViewHolder>() {

    private lateinit var vm: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemShowBinding.inflate(layoutInflater)
        vm = ViewModelProvider(fragment).get(HomeViewModel::class.java)
        return TracksAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TracksAdapterViewHolder, position: Int) {
        holder.binding.viewModel = vm
        holder.binding.show = list[position].show
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TracksAdapterViewHolder(val binding: ItemShowBinding) :
        RecyclerView.ViewHolder(binding.root)
}
