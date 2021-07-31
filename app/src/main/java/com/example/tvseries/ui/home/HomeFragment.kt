package com.example.tvseries.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tvseries.R
import com.example.tvseries.databinding.FragmentHomeBinding
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    lateinit var adapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showList.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgress.postValue(false)
            adapter = HomeAdapter(
                this,
                it
            )
            binding.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        viewModel.seasons.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgress.postValue(false)
            val bundle =
                bundleOf(
                    "Seasons" to Gson().toJson(it),
                    "Show" to Gson().toJson(viewModel.showSelected.value),
                    "Favorite" to true
                )
            findNavController().navigate(
                R.id.action_homeFragment_to_showFragment,
                bundle
            )
        })

        viewModel.showFavorites.observe(binding.lifecycleOwner!!, {
            findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
        })

        if (!::adapter.isInitialized) {
            viewModel.getSeries()
            adapter = HomeAdapter(this, ArrayList())
            binding.adapter = adapter
        } else {
            binding.adapter = adapter
        }

    }

}