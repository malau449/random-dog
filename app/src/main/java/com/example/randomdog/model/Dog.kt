package com.example.randomdog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_table")
data class Dog (

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var url : String?
)