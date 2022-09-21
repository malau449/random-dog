package com.example.randomdog.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.R
import com.example.randomdog.databinding.FragmentFavouriteDogsBinding
import com.example.randomdog.databinding.FragmentRandomDogBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteDogsFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteDogsBinding

    private lateinit var viewModel: FavouriteDogsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_dogs, container, false)
        viewModel = ViewModelProvider(this).get(FavouriteDogsViewModel::class.java)

        val view = binding.root

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        var recyclerView = view.findViewById<RecyclerView>(R.id.favouritesRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this.context,2)
        recyclerView.adapter = FavouritesAdapter(viewModel.FetchFavouriteDogs())


        return view
    }

    class FavouritesAdapter(dataList: List<String>): RecyclerView.Adapter<FavouritesAdapter.ViewHolder>(){

        var dataList = emptyList<String>()

        init {
            setData(dataList)
        }

        fun setData(dataList: List<String>){
            this.dataList = dataList
        }

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var view: ImageView

            init {
                view = itemView.findViewById(R.id.favouritesListItemImage)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_favourite_dog, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // Get the data model based on position
            var data = dataList[position]

            // Set item views based on your views and data model
            Picasso.get().load(data).into(holder.view)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }
}