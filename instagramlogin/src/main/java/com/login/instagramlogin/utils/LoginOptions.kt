package com.login.instagramlogin.utils

import com.login.instagramlogin.appinterface.OnInstLoginListener

class LoginOptions {
    /**
     * To get clientSecretID , clientSecretID ,redirectionURL create app on facebook developer console
     * & add you app their and get these keys from them
     * for more information check link
     * https://developers.facebook.com/docs/instagram-api/
     **/
    private var clientID = ""
    private var clientSecretID = ""
    private var redirectionURL = ""

    private val AUT_URL = "https://instagram.com/oauth/authorize/?"
    private val TOKEN_URL = "https://api.instagram.com/oauth/access_token"
    private val DATA_URL = "https://graph.instagram.com/"

    private var listener: OnInstLoginListener? = null

    companion object {
        fun init(): LoginOptions {
            return LoginOptions()
        }

    }

    fun setClientId(clientId: String): LoginOptions {
        if (clientId == ""){
            throw NullPointerException("Can't implement login without using client id")
        }
        this.clientID = clientId
        return this
    }

    internal fun getClientId(): String {
        return clientID
    }

    internal fun getToken():String{
        return TOKEN_URL
    }
    internal fun getData():String{
        return DATA_URL
    }

    internal fun getClientSecret(): String {
        return clientSecretID
    }

    internal fun getRedirectionUrl(): String {
        return clientSecretID
    }

    internal fun getLoginListener(): OnInstLoginListener {
        return listener!!
    }

    fun setClientSecret(clientSecret: String): LoginOptions {
        if (clientSecret == ""){
            throw NullPointerException("Can't implement login without using Client Secret ID")
        }
        this.clientSecretID = clientSecret
        return this
    }

    fun setRedirectionUrl(redirectionURL: String): LoginOptions {
        if (redirectionURL ==""){
            throw NullPointerException("Can't implement login without using Redirection Url")
        }
        this.redirectionURL = redirectionURL
        return this
    }

    fun build(listener: OnInstLoginListener): LoginOptions {
        this.listener = listener
        return this
    }

    internal fun getInstaAuthUrl():String{
        return  "${AUT_URL}client_id=${clientID}&redirect_uri=${redirectionURL}&scope=user_profile,user_media&response_type=code"
    }


}