package com.login.instagramlogin.utils

import android.util.Log

class L {

    companion object {
        val L_TAG = "InstagramLoginHelper"

        fun d(message: String) {
            Log.d(L_TAG, message)
        }

        fun i(message: String) {
            Log.i(L_TAG, message)
        }

        fun v(message: String) {
            Log.v(L_TAG, message)
        }

        fun e( message: String) {
            Log.e(L_TAG, message)

        }
    }

}