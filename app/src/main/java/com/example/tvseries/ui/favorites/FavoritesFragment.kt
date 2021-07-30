package com.example.tvseries.ui.favorites

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tvseries.R
import com.example.tvseries.databinding.FragmentFavoritesBinding
import com.example.tvseries.model.Show
import com.example.tvseries.model.ShowList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    lateinit var adapter: FavoritesAdapter
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModel()
    var favorites: ArrayList<Show>? = null
    private val type = object : TypeToken<ArrayList<Show>>() {}.type
    private var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = context?.getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        favorites = Gson().fromJson(prefs!!.getString("list", null), type)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorites,
            container,
            false
        )
        binding.viewModel = this.viewModel
        if (favorites != null) {
            adapter = FavoritesAdapter(this, favorites!!)
        } else {
            adapter = FavoritesAdapter(this, ArrayList())
        }
        binding.adapter = adapter
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
                    "Favorite" to false
                )
            findNavController().navigate(
                R.id.action_favoriteFragment_to_seasonFragment,
                bundle
            )
        })

        viewModel.remove.observe(binding.lifecycleOwner!!, {
            favorites?.remove(viewModel.remove.value!!)
            val editor: SharedPreferences.Editor? = prefs?.edit()
            editor!!.putString("list", Gson().toJson(favorites))
            editor.apply()
            adapter = FavoritesAdapter(this, favorites!!)
            adapter.notifyDataSetChanged()
            binding.adapter = adapter

            Toast.makeText(this.context, R.string.removed, Toast.LENGTH_LONG).show()
        })
    }
}