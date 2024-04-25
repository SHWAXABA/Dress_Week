package com.example.dressweek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils


class Shirt_Page : Fragment() {

    //private val rotateOpen: Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim)}
    //private val rotateClose: Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim)}
    //private val fromBottom: Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim)}
    //private val toBottom: Animation by lazy{AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shirt__page, container, false)
    }


    }
