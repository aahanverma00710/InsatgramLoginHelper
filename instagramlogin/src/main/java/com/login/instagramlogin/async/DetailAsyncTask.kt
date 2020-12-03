package com.login.instagramlogin.async

import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import com.login.instagramlogin.pojo.LoginSuccessData
import com.login.instagramlogin.utils.LoginOptions
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection

class DetailAsyncTask(
        var context: Context,
        var detailURLFull: String,
        var dialogWeb: Dialog, var listener: LoginOptions
    ) :
            AsyncTask<URL?, Int?, Long>() {
        var code: String? = null
        var fullName: String? = null
        var userId: String? = null


        override fun onPreExecute() {
        }


        override fun doInBackground(vararg p0: URL?): Long {
            val result: Long = 0
            try {
                val url = URL(detailURLFull)
                val httpsURLConnection = url.openConnection() as HttpsURLConnection

                httpsURLConnection.requestMethod = "GET"

                val response: String =
                        String(httpsURLConnection.inputStream.readBytes(), StandardCharsets.UTF_8)
                val jsonObject = JSONTokener(response).nextValue() as JSONObject

                fullName = jsonObject.getString("username")
                userId = jsonObject.getString("id")


            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: Long) {
            dialogWeb.dismiss()
            try {
                listener.getLoginListener().onLoginSuccess(LoginSuccessData(fullName!!,userId!!))
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }