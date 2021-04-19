package com.example.todoapp.data.api


import com.example.todoapp.data.api.UnsafeHttpClient.UnsafeClient.getUnsafeOkHttpClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {


    companion object{
        private  val BASE_URL = "http://192.168.29.94:8080/"
        var okHttpClient: OkHttpClient = getUnsafeOkHttpClient()
        @Volatile
        private var INSTANCE:Retrofit?=null
        fun getRetrofit():Retrofit{

         return INSTANCE?: synchronized(this){
             val instance=Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 //.client(okHttpClient)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
             INSTANCE=instance
             instance
         }
        }

    }

    val userApiService:UserApiService = getRetrofit().create(UserApiService::class.java)


}
