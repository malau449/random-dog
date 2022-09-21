package com.example.randomdog.model

import com.google.gson.annotations.SerializedName


data class RandomDog (

    @SerializedName("message" ) var message : String? = null,
    @SerializedName("status"  ) var status  : String? = null

)