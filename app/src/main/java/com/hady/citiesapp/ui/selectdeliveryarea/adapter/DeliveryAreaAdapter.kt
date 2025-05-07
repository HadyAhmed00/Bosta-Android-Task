package com.hady.citiesapp.ui.selectdeliveryarea.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hady.citiesapp.ui.selectdeliveryarea.model.AreaItemSelection
import com.hady.citiesapp.ui.selectdeliveryarea.model.CityItemSelection

class DeliveryAreaAdapter(
    private val onCitySelected: (CityItemSelection) -> Unit = {},
    private val onAreaSelected: (AreaItemSelection) -> Unit = {}
) : ListAdapter<Any, RecyclerView.ViewHolder>(DeliveryAreaDiffCallback()) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CityItemSelection -> VIEW_TYPE_CITY
            is AreaItemSelection -> VIEW_TYPE_AREA
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when(viewType){
            VIEW_TYPE_CITY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.hady.citiesapp.R.layout.item_city, parent, false)
                CityViewHolder(view, onCitySelected)
            }
            VIEW_TYPE_AREA -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(com.hady.citiesapp.R.layout.item_area, parent, false)
                AreaViewHolder(view, onAreaSelected)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is CityViewHolder -> {
                val cityItem = getItem(position) as CityItemSelection
                holder.bind(cityItem)
            }
            is AreaViewHolder -> {
                val areaItem = getItem(position) as AreaItemSelection
                holder.bind(areaItem)
            }
        }
    }


    companion object {
        const val VIEW_TYPE_CITY = 0
        const val VIEW_TYPE_AREA = 1
    }
}
