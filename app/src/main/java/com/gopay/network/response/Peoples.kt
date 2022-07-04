package com.gopay.network.response

import com.google.gson.annotations.SerializedName

data class Peoples(
    @SerializedName("name")
    val name:String,
    val height:String,
    val mass:String,
    val hair_color:String,
)
