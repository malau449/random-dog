package com.example.randomdog.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dog::class], version = 1)
abstract class DogDatabase: RoomDatabase() {
    abstract fun getDogDao(): DogDao
}