package com.example.randomdog.ui.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.randomdog.R
import com.example.randomdog.databinding.FragmentRandomDogBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomDogFragment : Fragment() {

    private lateinit var binding: FragmentRandomDogBinding

    private lateinit var viewModel: RandomDogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_random_dog, container, false)
        viewModel = ViewModelProvider(this)[RandomDogViewModel::class.java]

        val view = binding.root

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }
}

@BindingAdapter("app:imgUrl")
fun setImgUrl(view: ImageView, url: String?) {
    Picasso.get().load(url).into(view)
}

@BindingAdapter("app:imgSwitchBinding", "app:imgSwitchFalse", "app:imgSwitchTrue")
fun setImgSwitch(view: FloatingActionButton, switchSource: Boolean, switchFalse: Drawable, switchTrue: Drawable){
    if(switchSource){
        view.setImageDrawable(switchTrue)
    } else{
        view.setImageDrawable(switchFalse)
    }
}