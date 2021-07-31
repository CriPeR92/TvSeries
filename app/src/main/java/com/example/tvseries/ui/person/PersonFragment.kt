package com.example.tvseries.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tvseries.R
import com.example.tvseries.databinding.FragmentPersonBinding
import com.example.tvseries.model.CastCreditsResponse
import com.example.tvseries.model.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.viewmodel.ext.android.viewModel

class PersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    lateinit var adapter: PersonAdapter
    var person: Person? = null
    var series: ArrayList<CastCreditsResponse>? = null
    private val type = object : TypeToken<ArrayList<CastCreditsResponse>>() {}.type
    private val viewModel: PersonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = Gson().fromJson(
            arguments?.getString("Person"),
            Person::class.java
        )
        series = Gson().fromJson(arguments?.getString("CastSeries"), type)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_person,
            container,
            false
        )
        adapter = PersonAdapter(
            this,
            series!!
        )
        binding.viewModel = viewModel
        binding.adapter = adapter
        binding.person = this.person
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.seasons.observe(binding.lifecycleOwner!!, {
            val bundle =
                bundleOf(
                    "Seasons" to Gson().toJson(it),
                    "Show" to Gson().toJson(viewModel.showSelected.value),
                    "Favorite" to true
                )
            findNavController().navigate(
                R.id.action_personFragment_to_episodeFragment,
                bundle
            )
        })

    }
}