package com.app.pinchtozoom

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.pinchtozoom.api.responses.Users
import com.kotlinrxjava.demoapp.apis.ApiInterfaces
import com.kotlinrxjava.demoapp.utils.NetworkState
import com.kotlinrxjava.demoapp.utils.Utilities
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UsersNetworkDataSource(
    private val retrofitClient: ApiInterfaces,
    private val compositeDisposable: CompositeDisposable,
    private val userDetails: MutableLiveData<List<Users>>,
    private val networkState: MutableLiveData<NetworkState>

) {

    private val TAG: String = Utilities.getTAGName(javaClass.simpleName)

//    private val networkState = MutableLiveData<NetworkState>()
//    private val userDetails = MutableLiveData<List<Users>>()


    fun getNetworkState(): MutableLiveData<NetworkState> {
        return networkState
    }

    fun getUsersLiveData(): MutableLiveData<List<Users>> {
        return userDetails
    }

    fun getUsers() {
        Log.d(TAG, "getUsers()")
        networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                retrofitClient.getUsers()
                    .subscribeOn(Schedulers.io()).subscribe({
                        Log.d(TAG, "getUsers() subscribe : ")
                        userDetails.postValue(it)
                        networkState.postValue(NetworkState.LOADED)
                    },
                        {
                            it.printStackTrace()
                            Log.d(TAG, "getUsers() Exception : ${it.message}")
                            networkState.postValue(NetworkState.ERROR)
                        })
            )
        } catch (e: Exception) {
            Log.d(TAG, "Exception In getUsers() ${e.message}")
        }
    }


}