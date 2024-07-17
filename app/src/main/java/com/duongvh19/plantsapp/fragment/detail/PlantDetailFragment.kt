package com.duongvh19.plantsapp.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.duongvh19.plantsapp.data.model.Plant
import com.duongvh19.plantsapp.databinding.FragmentPlantDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantDetailFragment : Fragment() {
    private lateinit var binding : FragmentPlantDetailBinding
    private val plantDetailViewModel : PlantDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        lifecycleScope.launch {
            plantDetailViewModel.plant.collect {item ->
                item?.let {
                    setupView(it)
                }
            }
        }
    }

    private fun setupView(plant: Plant) {
        binding.description.text = plant.description
        binding.toolbar.title = plant.name
        context?.let {
            Glide.with(it)
                .load(plant.imageUrl)
                .override(Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.imThumb)
        }

    }
}