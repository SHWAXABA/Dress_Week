package com.example.dressweek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class ClothingAdaptor(private val clothingList:ArrayList<Clothing>):
    RecyclerView.Adapter<ClothingAdaptor.ViewHolder>() {


    fun deleteItem(position: Int) {
        if (position in 0 until clothingList.size) {
            clothingList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return clothingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = clothingList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.textLeading.text = currentItem.clothingDescriptor
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val textLeading: TextView = itemView.findViewById(R.id.textleading)

    }
}