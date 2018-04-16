package com.app.sample.mvvm.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.app.sample.mvvm.repository.data.User
import io.reactivex.Single

/**
 * Created by admin on 16/04/18.
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Single<List<User>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)
}