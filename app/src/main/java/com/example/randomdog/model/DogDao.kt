package com.example.randomdog.model

import androidx.room.*

@Dao
interface DogDao {

    @Insert
    fun insert(dog: Dog)

    @Update
    fun update(dog: Dog)

    @Delete
    fun delete(dog: Dog)

    @Query("SELECT * FROM dog_table")
    fun getAllDogs(): List<Dog>
}