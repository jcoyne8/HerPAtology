package com.jcoynemobile.herpatology

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "snake")
data class Snake (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="scientificName") val scientificName: String,
    @ColumnInfo(name="venom") val venom: String,
    @ColumnInfo(name="habitat") val habitat: String,
    @ColumnInfo(name="length") val length: String,
    @ColumnInfo(name="range") val range: String,
    @ColumnInfo(name="color") val color: String,
    @ColumnInfo(name="isNortheast") val isNortheast: Int,
    @ColumnInfo(name="isNorthwest") val isNorthwest: Int,
    @ColumnInfo(name="isSoutheast") val isSoutheast: Int,
    @ColumnInfo(name="isSouthwest") val isSouthwest: Int,
    @ColumnInfo(name="isCentral") val isCentral: Int,
    @ColumnInfo(name="snakeNotes") var snakeNotes: String
    )