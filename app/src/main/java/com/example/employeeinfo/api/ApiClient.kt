package com.example.employeeinfo.api

import com.example.employeeinfo.interfaces.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private var baseurl: String = "https://jsonplaceholder.typicode.com/"
    val getClient: ApiInterface
        get() {
            val gson = GsonBuilder().setLenient().create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder().baseUrl(baseurl).client(client).addConverterFactory(
                GsonConverterFactory.create(gson)).build()
            return retrofit.create(ApiInterface::class.java)
        }
}