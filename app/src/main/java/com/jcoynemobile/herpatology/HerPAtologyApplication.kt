package com.jcoynemobile.herpatology

import android.app.Application

class HerPAtologyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SnakeRepository.initialize(this)
    }
}