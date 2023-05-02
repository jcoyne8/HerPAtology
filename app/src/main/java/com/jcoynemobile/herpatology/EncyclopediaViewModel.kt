package com.jcoynemobile.herpatology

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EncyclopediaViewModel : ViewModel() {

    private val snakeRepository = SnakeRepository.get()
    private val snakes = mutableListOf<Snake>()

     init {
        viewModelScope.launch{
            snakes += loadSnakes()
        }
    }

     suspend fun loadSnakes(): List<Snake> {
        snakes += snakeRepository.getSnakes()
        if (snakes.isEmpty()) {
            snakeRepository.preloadSnakes()
        }
        return snakeRepository.getSnakes()
    }

    suspend fun loadNESnakes(): List<Snake> {
        snakes += snakeRepository.getNortheast()
        if (snakes.isEmpty()) {
            snakeRepository.getNortheast()
        }
        return snakeRepository.getNortheast()
    }

    suspend fun loadNWSnakes(): List<Snake> {
        snakes += snakeRepository.getNorthwest()
        if (snakes.isEmpty()) {
            snakeRepository.getNorthwest()
        }
        return snakeRepository.getNorthwest()
    }

    suspend fun loadSESnakes(): List<Snake> {
        snakes += snakeRepository.getSoutheast()
        if (snakes.isEmpty()) {
            snakeRepository.getSoutheast()
        }
        return snakeRepository.getSoutheast()
    }

    suspend fun loadSWSnakes(): List<Snake> {
        snakes += snakeRepository.getSouthwest()
        if (snakes.isEmpty()) {
            snakeRepository.getSouthwest()
        }
        return snakeRepository.getSouthwest()
    }

    suspend fun loadCSnakes(): List<Snake> {
        snakes += snakeRepository.getCentral()
        if (snakes.isEmpty()) {
            snakeRepository.getCentral()
        }
        return snakeRepository.getCentral()
    }

    suspend fun updateNotes(snakeNotes: String, id: Int) {
        snakeRepository.updateNotes(snakeNotes, id)
        return snakeRepository.updateNotes(snakeNotes, id)
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