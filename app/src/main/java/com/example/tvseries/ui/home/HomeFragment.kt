package com.example.tvseries.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

        /**
         * Observer to know when the friends are loaded, recyclerView is updated
         */
        viewModel.showList.observe(binding.lifecycleOwner!!, Observer {
            viewModel.hideProgress.postValue(0)
            adapter = HomeAdapter(
                this,
                it
            )
            binding.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        viewModel.seasons.observe(binding.lifecycleOwner!!, Observer {
            viewModel.hideProgress.postValue(0)
            val bundle =
                bundleOf("Seasons" to Gson().toJson(viewModel.seasons.value),
                                "Show" to Gson().toJson(viewModel.showSelected.value))
            findNavController().navigate(
                R.id.action_homeFragment_to_showFragment,
                bundle
            )
        })


        viewModel.getSeries()
        adapter = HomeAdapter(this, ArrayList())
        binding.adapter = adapter
    }

}