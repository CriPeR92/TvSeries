package com.example.tvseries.extension

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tvseries.R
import com.example.tvseries.model.Episode
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (imageUrl == null) {
        view.setBackgroundResource(R.color.black)
        view.setImageResource(R.drawable.ic_launcher_foreground)
    } else {
        Picasso.get()
            .load(imageUrl)
            .into(view)
    }

}

/**
 * Function to set adapter in recyclerView and set recyclerView features
 */
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    this.setHasFixedSize(true)
    this.adapter = adapter
}

@BindingAdapter("app:setRecycler")
fun setRecycler(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.run {
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

/**
 * bind to know when to hide or show a view
 */
@BindingAdapter("app:hideIfSaved")
fun hideIfSaved(view: View, number: Int) {
    view.visibility = if (number == 0) View.GONE else View.VISIBLE
}

/**
 * bind to know when to hide or show a view
 */
@BindingAdapter("app:hideHeader")
fun hideHeader(view: View, episode: Episode) {
    if (episode.season == episode.number) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}