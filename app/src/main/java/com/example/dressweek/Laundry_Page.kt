package com.example.dressweek

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Laundry_Page : Fragment() {

    private  lateinit var newRecyclerView: RecyclerView
    private lateinit var  newArrayList:ArrayList<Clothing>
    lateinit var imageId:Array<Int>
    lateinit var textLeading: Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Thread.setDefaultUncaughtExceptionHandler { _, e ->
            // Log the uncaught exception
            Log.e("GlobalExceptionHandler", "Unhandled exception occurred", e)
        }
        imageId = arrayOf(
            R.drawable.blackchino,
            R.drawable.blue_jeans,
            R.drawable.chinos,
            R.drawable.whiteformal,
            R.drawable.blazer,
            R.drawable.whiteshirt,
            R.drawable.kway,
            R.drawable.blacksweater,
        )
        textLeading = arrayOf(
            "Pants 10",
            "Pants 1",
            "Pants 6",
            "Shirt 4",
            "OverWear 10",
            "Shirt 3",
            "OverWear 5",
            "OverWear 2",

        )

        var findView =  inflater.inflate(R.layout.fragment_laundry__page, container, false)
       newRecyclerView = findView.findViewById<RecyclerView>(R.id.recyclerview)

        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Clothing>()
        getData()


        return findView
    }

    private fun getData() {
        for(i in imageId.indices){
            val clothes = Clothing(imageId[i],textLeading[i])
            newArrayList.add(clothes)
        }

        newRecyclerView.adapter = ClothingAdaptor(newArrayList)
        val adaptor = ClothingAdaptor(newArrayList)
        val swipegesture = object : SwipeGesture(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT->{
                        adaptor.deleteItem(viewHolder.adapterPosition)
                    }
                }


            }
        }
        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView(newRecyclerView)

    }


}