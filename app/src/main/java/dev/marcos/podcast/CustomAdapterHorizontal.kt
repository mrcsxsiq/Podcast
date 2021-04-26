package dev.marcos.podcast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.marcos.podcast.data.Region

class CustomAdapterHorizontal(
    private val context: Context,
    private val dataSet: List<Region>
) : RecyclerView.Adapter<CustomAdapterHorizontal.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_view)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.horizontal_list_featured, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val adapter = CustomAdapter(context = context, dataSet = dataSet[position].podcasts)
        viewHolder.recyclerView.adapter = adapter
        viewHolder.textView.text = dataSet[position].title
    }

    override fun getItemCount() = dataSet.size

}