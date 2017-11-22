package com.kotlinblueprint.besocial.ui.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.kotlinblueprint.besocial.*
import com.kotlinblueprint.besocial.util.*
import com.linkedin.platform.LISessionManager
import com.linkedin.platform.errors.LIAuthError
import com.linkedin.platform.listeners.AuthListener
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.linkedin.platform.errors.LIApiError
import com.linkedin.platform.listeners.ApiResponse
import com.linkedin.platform.listeners.ApiListener
import com.linkedin.platform.APIHelper
import org.json.JSONObject


class SplashActivity : AppCompatActivity() {
    private var isConnectedToAnySocialMedia: Boolean by DelegatedPreference(this, IS_CONNECTED_TO_SOCIAL_MEDIA, false)
    private var isConnectedToTwitter: Boolean by DelegatedPreference(this, IS_CONNECTED_TO_TWITTER, false)
    private var isConnectedToLinkedIn: Boolean by DelegatedPreference(this, IS_CONNECTED_TO_LINKEDIN, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViews()
    }

    private fun initViews() {
        if (isConnectedToAnySocialMedia) {
            startActivity<HomeActivity>()
        } else {
            initTwitterLogin()
            initLinkedInLogin()
        }
    }

    private fun initTwitterLogin() {
        btnLoginWithTwitter.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                toast("Successfully logged in for ${result.data.userName}")
                isConnectedToAnySocialMedia = true
                isConnectedToTwitter = true
                moveToHomeActivity()
            }

            override fun failure(exception: TwitterException) {
                toast(exception.message.toString())
            }
        }
    }

    private fun moveToHomeActivity() {
        startActivity<HomeActivity>()
        finish()
    }

    private fun initLinkedInLogin() {
        btnLoginLinkedIn.setOnClickListener({
            LISessionManager.getInstance(applicationContext)
                    .init(this, buildScope(), object : AuthListener {
                        override fun onAuthSuccess() {
                            isConnectedToAnySocialMedia = true
                            isConnectedToLinkedIn = true
                            Toast.makeText(applicationContext, "Success " + LISessionManager
                                    .getInstance(applicationContext)
                                    .session.accessToken.toString(),
                                    Toast.LENGTH_LONG).show()
                            moveToHomeActivity()
                            //postToLinkedIn()
                        }

                        override fun onAuthError(error: LIAuthError) {

                            Toast.makeText(applicationContext, "Failed " + error.toString(),
                                    Toast.LENGTH_LONG).show()
                        }
                    }, true)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                LINKEDIN_SUCCESS -> LISessionManager.getInstance(applicationContext)
                        .onActivityResult(this, requestCode, resultCode, data)
                TWITTER_SUCCESS -> btnLoginWithTwitter.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    fun postToLinkedIn() {
        val url = "https://api.linkedin.com/v1/people/~/shares"

        val body = JSONObject("{" +
                "\"comment\": \"Sample share\"," +
                "\"visibility\": { \"code\": \"anyone\" }," +
                "\"content\": { " +
                "\"title\": \"Sample share\"," +
                "\"description\": \"Testing the mobile SDK call wrapper!\"," +
                "\"submitted-url\": \"http://www.example.com/\"," +
                "\"submitted-image-url\": \"http://www.example.com/pic.jpg\"" +
                "}" +
                "}")

        val apiHelper = APIHelper.getInstance(applicationContext)
        apiHelper.postRequest(this, url, body, object : ApiListener {
            override fun onApiSuccess(apiResponse: ApiResponse) {
                toast("Successfully shared on your linked account")
            }

            override fun onApiError(liApiError: LIApiError) {
                toast("Error while sharing updates")
            }
        })
    }
}
