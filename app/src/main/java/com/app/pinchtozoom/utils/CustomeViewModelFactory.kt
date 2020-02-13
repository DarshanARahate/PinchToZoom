package com.kotlinrxjava.demoapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.pinchtozoom.UsersNetworkDataSource
import com.app.pinchtozoom.api.responses.Users
import com.app.pinchtozoom.ui.mainscreen.MainActivityRepository
import com.app.pinchtozoom.ui.mainscreen.MainActivityViewModel
import com.kotlinrxjava.demoapp.utils.NetworkState

class CustomeViewModelFactory : ViewModelProvider.Factory {

    lateinit var mainActivityRepository: MainActivityRepository
    lateinit var userDetails: MutableLiveData<List<Users>>
    lateinit var networkState: MutableLiveData<NetworkState>


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            MainActivityViewModel(mainActivityRepository, userDetails, networkState) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }

    }

    fun setMainActivityMutableLiveData(
        userDetails: MutableLiveData<List<Users>>,
        networkState: MutableLiveData<NetworkState>
    ) {
        this.userDetails = userDetails
        this.networkState = networkState

    }

    fun setMainActivityRepo(mainActivityRepository: MainActivityRepository) {
        this.mainActivityRepository = mainActivityRepository
    }

}