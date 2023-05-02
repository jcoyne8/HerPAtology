package com.jcoynemobile.herpatology

import android.content.Context
import androidx.room.Room
import com.jcoynemobile.herpatology.database.SnakeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

private const val DATABASE_NAME = "snake-database"

class SnakeRepository private constructor(context: Context) {

    private val database: SnakeDatabase = Room.databaseBuilder(
            context.applicationContext,
            SnakeDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    suspend fun getSnakes(): List<Snake> = database.snakeDao().getSnakes()
    suspend fun getSnake(id: Int): Snake = database.snakeDao().getSnake(id)
    suspend fun preloadSnakes() = database.snakeDao().preloadSnakes()
    suspend fun getNortheast(): List<Snake> = database.snakeDao().getNortheast()
    suspend fun getNorthwest(): List<Snake> = database.snakeDao().getNorthwest()
    suspend fun getSoutheast(): List<Snake> = database.snakeDao().getSoutheast()
    suspend fun getSouthwest(): List<Snake> = database.snakeDao().getSouthwest()
    suspend fun getCentral(): List<Snake> = database.snakeDao().getCentral()
    suspend fun updateNotes(snakeNotes: String, id: Int): Unit = database.snakeDao().updateNotes(snakeNotes, id)


    companion object {
        private var INSTANCE: SnakeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SnakeRepository(context)
            }
        }

        fun get(): SnakeRepository {
            return INSTANCE ?:
            throw IllegalStateException("SnakeRepository must be initialized")
        }
    }
}