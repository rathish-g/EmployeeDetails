package com.example.employeeinfo.interfaces

import com.example.employeeinfo.api.ResponseData
import com.example.employeeinfo.api.Result
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    fun getEmployeesInfo(): Call<List<Result?>?>?
}