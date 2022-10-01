package com.example.flixsterandroidapp

import com.google.gson.annotations.SerializedName

class Movie_Item {

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("id")
    var id: Int? = 0

    //TODO bookImageUrl
    @SerializedName("poster_path")
    var poster_path: String? = null


    //TODO description
    @SerializedName("overview")
    var overview: String? = null
}