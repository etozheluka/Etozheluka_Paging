package com.space.etozheluka_paging.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.etozheluka_paging.databinding.ItemBinding
import com.space.etozheluka_paging.model.MoviesDTO


@SuppressLint("NotifyDataSetChanged")
class MoviesAdapter : PagingDataAdapter<MoviesDTO.Result, MoviesAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoviesDTO.Result) {
            with(binding) {
                textView.text = item.title
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${item.posterPath}")
                    .into(imageView)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<MoviesDTO.Result>() {
            override fun areItemsTheSame(
                oldItem: MoviesDTO.Result,
                newItem: MoviesDTO.Result
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MoviesDTO.Result,
                newItem: MoviesDTO.Result
            ): Boolean =
                oldItem == newItem
        }
    }
}