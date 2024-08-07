package com.example.randomdog.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomdog.R
import com.example.randomdog.databinding.FragmentFavouriteDogsBinding
import com.example.randomdog.model.Dog
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteDogsFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteDogsBinding

    private lateinit var viewModel: FavouriteDogsViewModel

    private var columns = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        columns =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_dogs, container, false)
        viewModel = ViewModelProvider(this)[FavouriteDogsViewModel::class.java]

        val view = binding.root

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val recyclerView = view.findViewById<RecyclerView>(R.id.favouritesRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this.context, columns)

        viewModel.favouriteDogsList.observe(viewLifecycleOwner) {
            if (recyclerView.adapter == null) {
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

    class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

        private var dataList = emptyList<Dog>()

        init {
            setData(dataList)
        }

        private fun setData(dataList: List<Dog>) {
            this.dataList = dataList
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var view: ImageView

            init {
                view = itemView.findViewById(R.id.favouritesListItemImage)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_favourite_dog, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // Get the data model based on position
            val dog = dataList[position]

            // Set item views based on your views and data model
            Picasso.get().load(dog.url).into(holder.view)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        fun updateList(dataList: List<Dog>) {
            this.dataList = dataList
            notifyDataSetChanged()
        }
    }
}