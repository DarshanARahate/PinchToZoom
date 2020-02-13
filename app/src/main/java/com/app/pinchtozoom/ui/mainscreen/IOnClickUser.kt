package com.app.pinchtozoom.ui.mainscreen

import com.app.pinchtozoom.api.responses.Users

interface IOnClickUser {

    public fun onClickItem(user: Users, position: Int)
}