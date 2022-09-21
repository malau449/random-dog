package com.example.randomdog.ui.main

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
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
import com.example.randomdog.model.Dog
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteDogsFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteDogsBinding

    private lateinit var viewModel: FavouriteDogsViewModel

    private var coloumns = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coloumns = if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2
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
        recyclerView.layoutManager = GridLayoutManager(this.context, coloumns)

        viewModel.favouriteDogsList.observe(viewLifecycleOwner){
            if(recyclerView.adapter == null){
              recyclerView.adapter = FavouritesAdapter()
            }
            (recyclerView.adapter as FavouritesAdapter).updateList(it)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.FetchFavouriteDogs()
    }

    class FavouritesAdapter(): RecyclerView.Adapter<FavouritesAdapter.ViewHolder>(){

        var dataList = emptyList<Dog>()

        init {
            setData(dataList)
        }

        fun setData(dataList: List<Dog>){
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
            var dog = dataList[position]

            // Set item views based on your views and data model
            Picasso.get().load(dog.url).into(holder.view)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        public fun updateList(dataList: List<Dog>){
            this.dataList = dataList
            notifyDataSetChanged()
        }
    }
}