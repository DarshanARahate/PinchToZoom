package com.kotlinrxjava.demoapp.utils


class NetworkState(val status: Int, val message: String) {

    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(Constant.SUCCESS, "Success")
            LOADING = NetworkState(Constant.RUNNING, "Running")
            ERROR = NetworkState(Constant.FAILED, "Failed")
        }

    }



}