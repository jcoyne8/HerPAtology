package com.jcoynemobile.herpatology

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.jcoynemobile.herpatology.databinding.EncyclopediaItemBinding


class EncyclopediaItemViewHolder (private val binding: EncyclopediaItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(snake: Snake, onImageClicked: (Int) -> Unit) {
        var snakepic = getSnakeSource(snake.id)
        binding.imageView.setImageResource(snakepic)
        binding.textView.setText(snake.name)
        binding.imageView.setOnClickListener {
            onImageClicked(snake.id)
        }
    }

    fun getSnakeSource(source: Int): Int {
        val snakepic: Int
        when (source) {
            1 -> {snakepic = R.drawable.northern_copperhead}
            2 -> {snakepic = R.drawable.timber_rattlesnake}
            3 -> {snakepic = R.drawable.eastern_massasauga}
            4 -> {snakepic = R.drawable.eastern_wormsnake}
            5 -> {snakepic = R.drawable.kirtlands_snake}
            6 -> {snakepic = R.drawable.northern_racer}
            7 -> {snakepic = R.drawable.ring_necked_snake}
            8 -> {snakepic = R.drawable.eastern_hognose_snake}
            9 -> {snakepic = R.drawable.eastern_milksnake}
            10 -> {snakepic = R.drawable.northern_watersnake}
            11 -> {snakepic = R.drawable.northern_rough_greensnake}
            12 -> {snakepic = R.drawable.smooth_greensnake}
            13 -> {snakepic = R.drawable.eastern_ratsnake}
            14 -> {snakepic = R.drawable.queensnake}
            15 -> {snakepic = R.drawable.northern_brown_snake}
            16 -> {snakepic = R.drawable.northern_redbellied_snake}
            17 -> {snakepic = R.drawable.short_headed_garter_snake}
            18 -> {snakepic = R.drawable.ribbonsnake}
            19 -> {snakepic = R.drawable.eastern_garter_snake}
            20 -> {snakepic = R.drawable.mountain_earthsnake}
            21 -> {snakepic = R.drawable.eastern_smooth_earthsnake}
            else -> {snakepic = R.drawable.eastern_garter_snake}
        }
        return snakepic
    }
}

class EncyclopediaAdapter(
    private val gridItems: List<Snake>,
    private val onImageClicked: (Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EncyclopediaItemViewHolder(EncyclopediaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gridItem = gridItems[position]
        holder as EncyclopediaItemViewHolder

        holder.bind(gridItem, onImageClicked)
    }

    override fun getItemCount(): Int {
        return gridItems.size
    }
}