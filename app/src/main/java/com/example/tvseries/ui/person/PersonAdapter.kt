package com.example.tvseries.ui.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.databinding.ItemPersonCastBinding
import com.example.tvseries.model.CastCreditsResponse

class PersonAdapter(private var fragment: PersonFragment, private var list: ArrayList<CastCreditsResponse>) :
    RecyclerView.Adapter<PersonAdapter.PersonAdapterViewHolder>() {

    private lateinit var vm: PersonViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonCastBinding.inflate(layoutInflater)
        vm = ViewModelProvider(fragment).get(PersonViewModel::class.java)
        return PersonAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonAdapterViewHolder, position: Int) {
        holder.binding.viewModel = vm
        holder.binding.show = list[position]._embedded?.show
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PersonAdapterViewHolder(val binding: ItemPersonCastBinding) :
        RecyclerView.ViewHolder(binding.root)
}