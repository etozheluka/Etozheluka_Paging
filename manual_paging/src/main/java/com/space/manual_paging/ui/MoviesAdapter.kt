package com.space.manual_paging.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.manaul_paging.databinding.ItemBinding
import com.space.manual_paging.model.MoviesDTO

@SuppressLint("NotifyDataSetChanged")
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var list = ArrayList<MoviesDTO.Result>()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newList: List<MoviesDTO.Result>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
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
}