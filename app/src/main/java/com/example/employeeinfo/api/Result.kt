package com.example.employeeinfo.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Result(
    @SerializedName("id")
    var id:Int?,

    @SerializedName("name")
    var employeeName: String?,

    @SerializedName("username")
    var userName:String?,

    @SerializedName("email")
    var eMail: String?,

    @SerializedName("phone")
    var phoneNumber: String?,

    @SerializedName("website")
    var website: String?,

    @SerializedName("company")
    var company: Company?,

    @SerializedName("address")
    var address: Address?

):Serializable
