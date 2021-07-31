package com.example.tvseries.extension

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
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


@BindingAdapter(value = ["app:setHtml"])
fun setHtmlText(view: TextView, text: String?) {
    if (text != null) {
        view.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY);
    }
}

/**
 * Function to set adapter in recyclerView and set recyclerView features (GRID)
 */
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    this.setHasFixedSize(true)
    this.adapter = adapter
}

/**
 * Function to set adapter in recyclerView and set recyclerView features (VERTICAL)
 */
@BindingAdapter("app:setRecycler")
fun setRecycler(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.run {
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.setHasFixedSize(true)
        this.isNestedScrollingEnabled = false
        this.adapter = adapter
    }
}

/**
 * bind to know when to hide or show a view
 */
@BindingAdapter("app:hideIfNull")
fun hideIfNull(view: TextView, data: String?) {
    view.visibility = if (data == null || data == "") {
        View.GONE
    } else {
        view.text = data
        View.VISIBLE
    }
}

/**
 * bind to know when to hide or show a view
 */
@BindingAdapter("app:hideIfSaved")
fun hideIfSaved(view: View, number: Boolean) {
    view.visibility = if (!number) View.GONE else View.VISIBLE
}

@BindingAdapter("app:hideHeader")
fun hideHeader(view: View, episode: Episode) {
    if (episode.season == episode.number) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}