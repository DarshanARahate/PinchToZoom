package com.kotlinrxjava.demoapp.apis

import com.app.pinchtozoom.api.responses.Photos
import com.app.pinchtozoom.api.responses.Users
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterfaces {

    @GET("users")
    fun getUsers(): Single<List<Users>>

    @GET("/photos")
    fun getUsers1(): Single<List<Photos>>
}