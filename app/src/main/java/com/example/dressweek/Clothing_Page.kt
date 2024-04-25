package com.example.dressweek

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class Clothing_Page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       var fragbut = inflater.inflate(R.layout.fragment_clothing__page,container,false)

        var shirtbut = fragbut.findViewById<Button>(R.id.shirtbutton)
        shirtbut.setOnClickListener(){
           

        }
        return inflater.inflate(R.layout.fragment_clothing__page, container, false)
    }


}