package com.example.dressweek

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AccessoriesAdapter(private val imageItems: MutableList<Accessories>) :
    RecyclerView.Adapter<AccessoriesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.title_image)
        val textView: TextView = itemView.findViewById(R.id.textleading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = imageItems[position]
        holder.imageView.setImageURI(Uri.fromFile(currentItem.imageFile))
        holder.textView.text = currentItem.imageName
    }

    override fun getItemCount(): Int {
        return imageItems.size
    }
    fun deleteItem(position: Int) {
        imageItems.removeAt(position)
        notifyItemRemoved(position)
    }
}