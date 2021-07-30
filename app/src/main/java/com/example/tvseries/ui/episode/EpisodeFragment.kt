package com.example.tvseries.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tvseries.R
import com.example.tvseries.databinding.FragmentEpisodeBinding
import com.example.tvseries.databinding.FragmentShowBinding
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Show
import com.example.tvseries.ui.show.GenreAdapter
import com.example.tvseries.ui.show.SeasonsAdapter
import com.example.tvseries.ui.show.ShowViewModel
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeBinding
    var episode: Episode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        episode = Gson().fromJson(
            arguments?.getString("Episode"),
            Episode::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_episode,
            container,
            false
        )
        binding.episode = this.episode
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}