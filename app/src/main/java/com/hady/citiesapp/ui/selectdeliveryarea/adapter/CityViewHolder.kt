package com.hady.citiesapp.ui.selectdeliveryarea.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hady.citiesapp.databinding.ItemCityBinding
import com.hady.citiesapp.ui.selectdeliveryarea.model.CityItemSelection

class CityViewHolder(
    itemView: View,
    private val onCitySelected: (CityItemSelection) -> Unit = {}
) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemCityBinding.bind(itemView)
    fun bind(city: CityItemSelection) {
        binding.cityName.text = city.name
        binding.cityArrow.setImageResource(
            if (city.isSelected) {
                com.hady.citiesapp.R.drawable.ic_arrow_up
            } else {
                com.hady.citiesapp.R.drawable.ic_arrow_down
            }
        )

        binding.linearLayout.setOnClickListener {
            onCitySelected(city)
        }
    }
}