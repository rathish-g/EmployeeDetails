package com.example.employeeinfo.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(
    @SerializedName("street")
    var street: String?,

    @SerializedName("suite")
    var suite: String?,

    @SerializedName("city")
    var city: String?,

    @SerializedName("zipcode")
    var zipcode: String?,

    @SerializedName("geo")
    var geo: Geo?

):Serializable
