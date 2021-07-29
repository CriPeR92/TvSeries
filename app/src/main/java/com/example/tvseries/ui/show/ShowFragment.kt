package com.example.tvseries.ui.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tvseries.R
import com.example.tvseries.databinding.FragmentShowBinding
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Show
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.viewmodel.ext.android.viewModel

class ShowFragment : Fragment() {

    private lateinit var binding: FragmentShowBinding
    private val viewModel: ShowViewModel by viewModel()
    var seasons: ArrayList<Episode>? = null
    private var showSelected: Show? = null
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

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
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

        genderAdapter = GenreAdapter(viewModel.showSelected.value?.genres!!)
        seasonsAdapter = SeasonsAdapter(seasons!!)
        binding.seasonAdapter = seasonsAdapter
        binding.genderAdapter = genderAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}