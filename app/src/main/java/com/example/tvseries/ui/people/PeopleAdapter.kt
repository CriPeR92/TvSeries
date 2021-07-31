package com.example.tvseries.ui.people


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.databinding.ItemPeopleBinding
import com.example.tvseries.model.PeopleList

class PeopleAdapter(private var fragment: PeopleFragment, var list: ArrayList<PeopleList>) :
    RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder>() {

    private lateinit var vm: PeopleViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(layoutInflater)
        vm = ViewModelProvider(fragment).get(PeopleViewModel::class.java)
        return PeopleAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleAdapterViewHolder, position: Int) {
        holder.binding.viewModel = vm
        holder.binding.person = list[position].person
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PeopleAdapterViewHolder(val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root)
}