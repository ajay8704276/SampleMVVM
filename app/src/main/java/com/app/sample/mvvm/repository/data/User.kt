package com.app.sample.mvvm.repository.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by admin on 16/04/18.
 */
@Entity(tableName = "users")
data class User(

        @PrimaryKey
        @ColumnInfo(name = "email")
        val email: String,


        @ColumnInfo(name = "firstName")
        val firstName :String,



        @ColumnInfo(name = "lastName")
        val lastName :String

)