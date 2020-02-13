package com.app.pinchtozoom.ui.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.pinchtozoom.UsersNetworkDataSource
import com.app.pinchtozoom.api.responses.Users
import com.kotlinrxjava.demoapp.utils.NetworkState
import com.kotlinrxjava.demoapp.utils.Utilities
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    private var mainActivityRepository: MainActivityRepository,
    private val userDetails: MutableLiveData<List<Users>>,
    private val networkState: MutableLiveData<NetworkState>
) :
    ViewModel() {

    private val TAG: String = Utilities.getTAGName(javaClass.simpleName)
    private val compositeDisposableUsers = CompositeDisposable()
    private lateinit var usersList: MutableList<Users>

    public fun removeUserData(pos: Int) {
        usersList = mutableListOf<Users>()
        usersList.addAll(userDetails.value!!)
        usersList.removeAt(pos)
        userDetails.value = usersList

    }

    public fun getUserListLiveData(): LiveData<List<Users>> {
        Log.d(TAG, "getUserListLiveData() : ${userDetails.value}")
        if (userDetails.value !is List<Users> || userDetails.value?.size == 0) {
            mainActivityRepository.getUsersLiveData()
        }
        return userDetails
    }

    public fun getNetworkStateUsers(): LiveData<NetworkState> {
        return networkState
    }

//    val userLiveData: LiveData<List<Users>> by lazy {
//        mainActivityRepository.getUsersLiveData(compositeDisposableUsers)
//    }
//
//    val networkStateUsers: LiveData<NetworkState> by lazy {
//        mainActivityRepository.getUsersNetworkStatus()
//    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposableUsers.dispose()
    }


}