package com.hady.citiesapp.ui.selectdeliveryarea.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hady.citiesapp.databinding.ItemAreaBinding
import com.hady.citiesapp.ui.selectdeliveryarea.model.AreaItemSelection

class AreaViewHolder(
    itemView: View,
    private val onAreaSelected: (AreaItemSelection) -> Unit = {}
) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemAreaBinding.bind(itemView)
    fun bind(area: AreaItemSelection) {
        binding.areaName.text = area.name
        binding.uncovered.isVisible = area.isUnCovered

        binding.linearLayout.setOnClickListener {
            onAreaSelected(area)
        }
    }
}