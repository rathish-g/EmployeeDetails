package com.example.employeeinfo.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company(
    @SerializedName("name")
    var companyName: String?,

    @SerializedName("catchPhrase")
    var catchPhrase: String?,

    @SerializedName("bs")
    var bs: String?

): Serializable

