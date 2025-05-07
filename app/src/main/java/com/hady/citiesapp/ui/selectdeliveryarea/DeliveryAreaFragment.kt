package com.hady.citiesapp.ui.selectdeliveryarea

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hady.citiesapp.databinding.FragmentDeliviryAreaBinding
import com.hady.citiesapp.ui.selectdeliveryarea.adapter.DeliveryAreaAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.hady.citiesapp.R
import com.hady.citiesapp.domain.utils.Resource
import com.hady.citiesapp.domain.utils.hideKeyboard

@AndroidEntryPoint
class DeliveryAreaFragment : Fragment() {
    private val viewModel: DeliveryAreaViewModel by viewModels()
    private lateinit var binding : FragmentDeliviryAreaBinding
    private lateinit var adapter: DeliveryAreaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_deliviry_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeliviryAreaBinding.bind(view)
        binding.search = viewModel.searchTextLiveData

        // handle buttons
        binding.retryButton.setOnClickListener { viewModel.refresh() }

        handleRecyclerView()
        handleKeyboard()
        handleObservers()
    }

    private fun handleObservers() {
        viewModel.allCitiesStatus.observe(viewLifecycleOwner) { status ->
            binding.progressBarContainer.isVisible = status is Resource.Loading
            binding.errorScreen.isVisible = status is Resource.Error
        }

        viewModel.citiesLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun handleKeyboard() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy != 0)
                    context?.hideKeyboard(recyclerView)
            }
        })
    }

    private fun handleRecyclerView() {
        // init adapter
        adapter = DeliveryAreaAdapter(
            onCitySelected = {
                viewModel.onCitySelected(it)
            },
            onAreaSelected = {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.selected, it.name), Toast.LENGTH_SHORT
                ).show()
            }
        )

        // bind recyclerView
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}