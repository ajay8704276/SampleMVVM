package com.app.sample.mvvm

import android.app.Application
import android.arch.persistence.room.Room
import com.app.sample.mvvm.repository.UserRepository
import com.app.sample.mvvm.repository.api.UserApi
import com.app.sample.mvvm.repository.db.AppDatabase
import com.app.sample.mvvm.viewmodel.UserListViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Created by admin on 16/04/18.
 */
class App: Application() {

    companion object {

        private lateinit var retrofit: Retrofit
        private lateinit var userApi: UserApi
        private lateinit var userRepository: UserRepository
        private lateinit var userListViewModel: UserListViewModel
        private lateinit var appDatabase: AppDatabase


        fun injectUserApi() = userApi

        fun injectUserListViewModel() = userListViewModel

        fun injectUserDao()  = appDatabase.userDao()


    }

    override fun onCreate() {
        super.onCreate()

        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())


        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://randomapi.com/api/")
                .build()


        userApi = retrofit.create(userApi::class.java)

        appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java,"mvvm-database").build()


        userRepository = UserRepository(userApi, appDatabase.userDao())
        userListViewModel = UserListViewModel(userRepository)
    }


}