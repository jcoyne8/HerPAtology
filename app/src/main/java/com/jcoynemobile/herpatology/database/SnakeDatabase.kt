package com.jcoynemobile.herpatology.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jcoynemobile.herpatology.Snake

@Database(entities = [ Snake::class ], version=6)
abstract class SnakeDatabase : RoomDatabase() {
    abstract fun snakeDao(): SnakeDao
}