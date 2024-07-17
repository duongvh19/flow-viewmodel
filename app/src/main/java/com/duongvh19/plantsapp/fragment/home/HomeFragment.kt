package com.duongvh19.plantsapp.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.duongvh19.plantsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val plantViewModel : PlantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantsAdapter = PlantsAdapter {
            val bundle = Bundle().apply {
                putParcelable("plant_item", it)
            }
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPlantDetailFragment(it))
        }

        lifecycleScope.launch {
            plantViewModel.plants.collect {
                plantsAdapter.submitList(it)
            }
        }

        binding.plantsList.adapter = plantsAdapter


    }
}