package com.gopay.network.response

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val next: String?,

    @SerializedName("previous")
    val previous: String,

    @SerializedName("results")
    val results: List<Peoples>

)
