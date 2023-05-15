package com.example.ivideo.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ivideo.R
import com.example.ivideo.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {

    lateinit var binding:FragmentThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        FragmentThreeBinding.inflate(layoutInflater,container,false)



        return binding.root
    }
}