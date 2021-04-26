package dev.marcos.podcast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.marcos.podcast.data.Podcast

class CustomAdapterViewPager(
    private val context: Context,
    private val dataSet: List<Podcast>
) : RecyclerView.Adapter<CustomAdapterViewPager.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_featured, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val url = dataSet[position % dataSet.size].image
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(viewHolder.imageView)
    }

    override fun getItemCount() = Int.MAX_VALUE

}