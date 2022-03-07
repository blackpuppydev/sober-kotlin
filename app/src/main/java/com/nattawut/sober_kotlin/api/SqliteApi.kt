package com.nattawut.sober_kotlin.api

import com.nattawut.sober_kotlin.constance.DBConst
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SqliteApi {

    companion object{

        private const val BASE_URL = "http://192.168.1.34"
        //query

        fun create() : SqliteApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(SqliteApi::class.java)

        }
    }


//    @GET("/admin")
//    fun getAdmin(@Query(DBConst.TABLE_NAME_ADMIN) admin:String): Call<GetAdmin>

}