package com.app.pinchtozoom.ui.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.pinchtozoom.UsersNetworkDataSource
import com.app.pinchtozoom.api.responses.Users
import com.kotlinrxjava.demoapp.apis.ApiInterfaces
import com.kotlinrxjava.demoapp.utils.NetworkState
import com.kotlinrxjava.demoapp.utils.Utilities
import io.reactivex.disposables.CompositeDisposable

public class MainActivityRepository(
    private var apiInterfaces: ApiInterfaces,
    private var usersNetworkDataSource: UsersNetworkDataSource
) {

    private val TAG: String = Utilities.getTAGName(javaClass.simpleName)
//    var userDetails: MutableLiveData<List<Users>>
//        get() = userDetails
//        set(value) {
//            userDetails = value
//        }
//    var networkState: MutableLiveData<NetworkState>
//        get() = networkState
//        set(value) {
//            networkState = value
//        }

    fun getUsersLiveData() {
        Log.d(TAG, "getUsersLiveData()")
        usersNetworkDataSource.getUsers()
    }


//    fun getUsersLiveData(compositeDisposable: CompositeDisposable): LiveData<List<Users>> {
//        Log.d(TAG, "getUsersLiveData()")
//
//        usersNetworkDataSource =
//            UsersNetworkDataSource(apiInterfaces, compositeDisposable, userDetails, networkState)
//        usersNetworkDataSource.getUsers()
//        return usersNetworkDataSource.getUsersLiveData()
//    }
//
//    fun getUsersNetworkStatus(): LiveData<NetworkState> {
//        Log.d(TAG, "getUsersNetworkStatus()")
//        return usersNetworkDataSource.getNetworkState()
//    }

}

