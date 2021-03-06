package com.prathamesh.weather_lookup.views.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prathamesh.weather_lookup.databinding.RowDetailsBinding
import com.prathamesh.weather_lookup.models.ForecastModel

class DetailsAdapter(
    private val details: List<ForecastModel>,
    private val onClick: (ForecastModel) -> Unit
) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    class ViewHolder(
        private val rowBinding: RowDetailsBinding
    ) : RecyclerView.ViewHolder(rowBinding.root) {

        fun bind(model: ForecastModel, onClick: (ForecastModel) -> Unit) {
            rowBinding.apply {
                clMain.setOnClickListener { onClick(model) }
                tvWeather.text = model.weatherList?.firstOrNull()?.description ?: ""
                tvTemp.text = model.main?.temp?.toString() ?: ""

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(details[position], onClick)
    }

    override fun getItemCount() = details.size
}