package com.app.sample.mvvm.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.app.sample.mvvm.repository.data.User

/**
 * Created by admin on 16/04/18.
 */

@Database(entities = arrayOf(User::class),version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao() :UserDao
}