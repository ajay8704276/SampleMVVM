package com.app.sample.mvvm.viewmodel

import com.app.sample.mvvm.repository.UserRepository
import com.app.sample.mvvm.viewmodel.data.UsersList
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by admin on 16/04/18.
 */
class UserListViewModel(val userRepository: UserRepository) {

    fun getUsers(): Observable<UsersList> {
        return userRepository.getUsers()
                .debounce(400, TimeUnit.MILLISECONDS)
                .map {
                    Timber.d("Mapping users to UIData...")
                    UsersList(it.take(10), "Top 10 Users")
                }
                .onErrorReturn {
                    UsersList(emptyList(), "An error occurred", it)
                }
    }
}