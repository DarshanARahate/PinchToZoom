package com.kotlinrxjava.demoapp.utils

object Utilities {

    fun getTAGName(simpleName: String?) : String {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }




}