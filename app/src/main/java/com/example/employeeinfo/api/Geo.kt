package com.example.employeeinfo.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geo(
    @SerializedName("lat")
    var lattitude: String?,

    @SerializedName("lng")
    var longitude: String?
):Serializable
