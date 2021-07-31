package com.example.tvseries.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tvseries.R
import com.example.tvseries.databinding.FragmentPeopleBinding
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.viewModel

class PeopleFragment : Fragment() {

    lateinit var adapter: PeopleAdapter
    private lateinit var binding: FragmentPeopleBinding
    private val viewModel: PeopleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_people,
            container,
            false
        )
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.peopleList.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgress.postValue(false)
            adapter = PeopleAdapter(
                this,
                it
            )
            binding.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        viewModel.seriesPerson.observe(binding.lifecycleOwner!!, {
            viewModel.hideProgress.postValue(false)
            val bundle =
                bundleOf(
                    "Person" to Gson().toJson(viewModel.peopleSelected.value),
                    "CastSeries" to Gson().toJson(it)
                )
            findNavController().navigate(
                R.id.action_peopleFragment_to_personFragment,
                bundle
            )
        })

        if (!::adapter.isInitialized) {
            viewModel.getPeople()
            adapter = PeopleAdapter(this, ArrayList())
            binding.adapter = adapter
        } else {
            binding.adapter = adapter
        }

    }

}