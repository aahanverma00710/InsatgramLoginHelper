package com.login.instagramlogin.async

import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import com.login.instagramlogin.utils.LoginOptions
import org.json.JSONObject
import org.json.JSONTokener
import java.io.OutputStreamWriter
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection

class LoadRedirectUrlForInsta(
        var code: String,
        var activity: Context,
        var dialogWeb: Dialog, var options: LoginOptions
    ) :
            AsyncTask<URL?, Int?, Long>() {

        var accessTokenString: String? = null
        var userId: String? = null
        var detailURLFull: String? = null


        override fun onPreExecute() {
        }


        override fun doInBackground(vararg p0: URL?): Long {
            val result: Long = 0
            val tokenURLFull =
                    "${options.getToken()}?client_id=${options.getClientId()}" +
                            "&client_secret=${options.getClientSecret()}" +
                            "&grant_type=authorization_code&redirect_uri=${options.getRedirectionUrl()}"
            try {
                val url = URL(tokenURLFull)
                val httpsURLConnection = url.openConnection() as HttpsURLConnection
                httpsURLConnection.requestMethod = "POST"
                httpsURLConnection.doInput = true
                httpsURLConnection.doOutput = true
                val outputStreamWriter = OutputStreamWriter(httpsURLConnection.outputStream)
                outputStreamWriter.write(
                        "client_id=" + options.getClientId() +
                                "&client_secret=" + options.getClientSecret() +
                                "&grant_type=authorization_code" +
                                "&redirect_uri=" +options.getRedirectionUrl() +
                                "&code=" + code.replace("#_", "")
                )
                outputStreamWriter.flush()
                /*val response: String =
                        CommonUtils.streamToString(httpsURLConnection.inputStream)*/
                val response=
                        String(httpsURLConnection.inputStream.readBytes(), StandardCharsets.UTF_8)
                val jsonObject = JSONTokener(response).nextValue() as JSONObject
                accessTokenString = jsonObject.getString("access_token") //Here is your ACCESS TOKEN
                userId = jsonObject.getString("user_id")


            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: Long) {
            dialogWeb.dismiss()
            detailURLFull =
                    "${options.getData()}$userId?fields=id,username,email&access_token=$accessTokenString"
            DetailAsyncTask(activity, detailURLFull!!, dialogWeb,options).execute()

        }
    }