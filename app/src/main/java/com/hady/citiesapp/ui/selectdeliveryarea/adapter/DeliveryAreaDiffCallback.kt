package com.hady.citiesapp.ui.selectdeliveryarea.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hady.citiesapp.ui.selectdeliveryarea.model.AreaItemSelection
import com.hady.citiesapp.ui.selectdeliveryarea.model.CityItemSelection

class DeliveryAreaDiffCallback: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem is AreaItemSelection && newItem is AreaItemSelection && oldItem.id == newItem.id
                || oldItem is CityItemSelection && newItem is CityItemSelection && oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem is AreaItemSelection && newItem is AreaItemSelection && oldItem == newItem
                || oldItem is CityItemSelection && newItem is CityItemSelection && oldItem == newItem
    }
}