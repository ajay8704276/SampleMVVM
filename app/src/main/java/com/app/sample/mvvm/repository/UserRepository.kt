package com.app.sample.mvvm.repository

import com.app.sample.mvvm.repository.api.UserApi
import com.app.sample.mvvm.repository.data.User
import com.app.sample.mvvm.repository.db.UserDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by admin on 16/04/18.
 */
class UserRepository(val userApi: UserApi, val userDao: UserDao) {

    fun getUsers():Observable<List<User>>{
        return Observable.concatArray(

                getUserFromDb(),
                getUserFromApi()
        )
    }

    private fun getUserFromDb(): Observable< List<User>>{

        return userDao.getUsers().filter {
            it.isEmpty()
        }.toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} users from DB...")
                }

    }

    private fun getUserFromApi(): Observable< List<User>>{

        return userApi.getUsers().doOnNext {
            Timber.d("Dispatching ${it.size} users from API...")
            storeUsersInDb(it)
        }

    }

    private fun storeUsersInDb(users: List<User>) {
        Observable.fromCallable { userDao.insertAll(users) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe{
                    Timber.d("Inserted ${users.size} users from API in DB...")
                }
    }
}