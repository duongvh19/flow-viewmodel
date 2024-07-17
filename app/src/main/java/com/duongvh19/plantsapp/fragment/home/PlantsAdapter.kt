package com.duongvh19.plantsapp.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.duongvh19.plantsapp.data.model.Plant
import com.duongvh19.plantsapp.databinding.ItemPlantBinding


class PlantsAdapter(private val onClickItem: (Plant) -> Unit) :
    ListAdapter<Plant, PlantsAdapter.PlantViewHolder>(DiffCallback) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        context = parent.context
        val binding = ItemPlantBinding.inflate(LayoutInflater.from(context),
        parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.setOnClickListener{
            onClickItem(currentItem)
        }
        holder.bindItem(currentItem, context)
    }

    class PlantViewHolder(private val binding: ItemPlantBinding)
        : ViewHolder(binding.root) {
            fun bindItem(item : Plant, context : Context) {
                binding.title.text = item.name
                Glide.with(context)
                    .load(item.imageUrl)
                    .override(Target.SIZE_ORIGINAL)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(binding.plantImage)

            }
        }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Plant> () {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return  oldItem.plantId == newItem.plantId
            }

            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem == newItem
            }

        }
    }

}