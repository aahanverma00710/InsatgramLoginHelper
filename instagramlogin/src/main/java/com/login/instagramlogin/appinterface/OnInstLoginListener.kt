package com.login.instagramlogin.appinterface

import com.login.instagramlogin.pojo.LoginSuccessData

interface OnInstLoginListener {
    fun onLoginSuccess(data: LoginSuccessData)
    fun onFailure(message:String)
}