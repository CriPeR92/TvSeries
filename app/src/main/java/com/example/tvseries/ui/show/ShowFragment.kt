package com.example.tvseries.ui.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tvseries.R
import com.example.tvseries.databinding.FragmentShowBinding
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Show
import com.example.tvseries.ui.home.HomeAdapter
import com.example.tvseries.ui.home.HomeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.viewmodel.ext.android.viewModel

class ShowFragment : Fragment() {

    private lateinit var binding: FragmentShowBinding
    private val viewModel: ShowViewModel by viewModel()
    var seasons: ArrayList<Episode>? = null
    private val type = object : TypeToken<ArrayList<Episode>>() {}.type
    lateinit var genderAdapter: GenreAdapter
    lateinit var seasonsAdapter: SeasonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seasons = Gson().fromJson(arguments?.getString("Seasons"), type)
        viewModel.showSelected.value = Gson().fromJson(
            arguments?.getString("Show"),
            Show::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_show,
            container,
            false
        )
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        genderAdapter = GenreAdapter(this, viewModel.showSelected.value?.genres!!)
        seasonsAdapter = SeasonsAdapter(this, seasons!!)
        binding.seasonAdapter = seasonsAdapter
        binding.genderAdapter = genderAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.episodeSelected.observe(binding.lifecycleOwner!!, Observer {
            val bundle =
                bundleOf("Episode" to Gson().toJson(viewModel.episodeSelected.value))
            findNavController().navigate(
                R.id.action_seasonFragment_to_episodeFragment,
                bundle
            )
        })
    }
}