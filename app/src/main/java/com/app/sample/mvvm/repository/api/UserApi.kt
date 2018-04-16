package com.app.sample.mvvm.repository.api

import com.app.sample.mvvm.repository.data.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by admin on 16/04/18.
 */
interface UserApi {

    @GET("6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
    fun getUsers(): Observable<List<User>>
}