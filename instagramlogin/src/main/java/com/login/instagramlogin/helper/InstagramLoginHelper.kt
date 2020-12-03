package com.login.instagramlogin.helper

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.view.ViewGroup
import android.view.Window
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.login.instagramlogin.R
import com.login.instagramlogin.appinterface.OnInstLoginListener
import com.login.instagramlogin.utils.LoginOptions

object InstagramLoginHelper {


    fun doLogin(ctz: Context, data: LoginOptions) {
        showLoginDialog(ctz, data)
    }

    private fun showLoginDialog(context: Context, loginOptions: LoginOptions) {
        var dialogWeb: Dialog? = null
        dialogWeb = Dialog(context)
        dialogWeb.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogWeb.setCanceledOnTouchOutside(true)
        dialogWeb.setContentView(R.layout.dialog_web_view)
        dialogWeb.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )
        val webView = dialogWeb.findViewById(R.id.webView) as WebView
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL;

        dialogWeb.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        webView.webViewClient = MyLoginListenerWebViewClient(context,dialogWeb,loginOptions)
        webView.settings.javaScriptEnabled = true
        webView.clearCache(true)
        webView.clearFormData()
        webView.clearHistory()
        webView.loadUrl(loginOptions.getInstaAuthUrl())
        dialogWeb.show()
    }
    class MyLoginListenerWebViewClient(val activity: Context, val dialogWeb: Dialog, val options: LoginOptions) :
            WebViewClient() {


        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            if (request.url.toString().startsWith(options.getRedirectionUrl())) {
                startAsyncTaskForLogin(request.url.toString(), activity, dialogWeb,options)
                return true
            }
            return false
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith("https")) {
                startAsyncTaskForLogin(url, activity, dialogWeb,options)
                return true
            }
            return false
        }


        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)



        }

    }

    fun startAsyncTaskForLogin(url: String, activity: Context, dialogWeb: Dialog,options: LoginOptions) {
        if (url.contains("code")) {
          //  MyAsyncTask(code, activity, dialogWeb,listener).execute()
        } else if (url.contains("error")) {
            val temp = url.split("=").toTypedArray()
            options.getLoginListener().onFailure("Something Went Wrong Please try again")
        }

    }

}