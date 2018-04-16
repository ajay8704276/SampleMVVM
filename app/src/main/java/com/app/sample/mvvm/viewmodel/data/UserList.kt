package com.app.sample.mvvm.viewmodel.data

import com.app.sample.mvvm.repository.data.User

/**
 * Created by admin on 16/04/18.
 */
data class UsersList(val users: List<User>, val message: String, val error: Throwable? = null)