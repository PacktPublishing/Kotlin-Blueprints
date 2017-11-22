package com.kotlinblueprint.besocial.ui.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import com.kotlinblueprint.besocial.R
import com.kotlinblueprint.besocial.data.remote.MyTwitterApiClient
import com.kotlinblueprint.besocial.extensions.gone
import com.kotlinblueprint.besocial.extensions.visible
import com.kotlinblueprint.besocial.util.*
import com.linkedin.platform.APIHelper
import com.linkedin.platform.LISessionManager
import com.linkedin.platform.errors.LIApiError
import com.linkedin.platform.errors.LIAuthError
import com.linkedin.platform.listeners.ApiListener
import com.linkedin.platform.listeners.ApiResponse
import com.linkedin.platform.listeners.AuthListener
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.User
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.linkedin_user.*
import kotlinx.android.synthetic.main.twitter_user.*
import org.jetbrains.anko.toast


class ProfileActivity : AppCompatActivity() {
    private var isConnectedToAnySocialMedia: Boolean by DelegatedPreference(this, IS_CONNECTED_TO_SOCIAL_MEDIA, false)
    private var isConnectedToTwitter: Boolean by DelegatedPreference(this, IS_CONNECTED_TO_TWITTER, false)
    private var isConnectedToLinkedIn: Boolean by DelegatedPreference(this, IS_CONNECTED_TO_LINKEDIN, false)

    val TAG = ProfileActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        checkTwitterIsConnected()
        checkLinkedInIsConnected()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun checkLinkedInIsConnected() {
        if (isConnectedToLinkedIn) {
            cardLinkedInUser.visible()
            btnLoginLinkedIn.gone()
            getLinkedInUser()
        } else {
            cardLinkedInUser.gone()
            btnLoginLinkedIn.visible()
            initLinkedInLogin()
        }
    }

    private fun initLinkedInLogin() {
        btnLoginLinkedIn.setOnClickListener({
            LISessionManager.getInstance(applicationContext)
                    .init(this, buildScope(), object : AuthListener {
                        override fun onAuthSuccess() {
                            isConnectedToAnySocialMedia = true
                            isConnectedToLinkedIn = true
                            checkLinkedInIsConnected()
                            toast("Connected to linked in, Token is ${LISessionManager
                                    .getInstance(applicationContext)
                                    .session.accessToken}")
                        }

                        override fun onAuthError(error: LIAuthError) {
                            //toast("Error connecting to linked in $error")
                        }
                    }, true)
        })
    }

    private fun checkTwitterIsConnected() {
        if (isConnectedToTwitter) {
            cardTwitterUser.visible()
            btnLoginWithTwitter.gone()
            getTwitterUser()
        } else {
            cardTwitterUser.gone()
            btnLoginWithTwitter.visible()
            initTwitterLogin()
        }
    }

    private fun getLinkedInUser() {
        val url = "https://api.linkedin.com/v1/people/~?format=json"
        val apiHelper = APIHelper.getInstance(applicationContext)
        apiHelper.getRequest(this, url, object : ApiListener {
            override fun onApiSuccess(apiResponse: ApiResponse) {
                Log.v(TAG, apiResponse.responseDataAsJson.toString())
                val (id, firstName, lastName, headline, siteStandardProfileRequest) = Gson().fromJson(apiResponse.responseDataAsJson.toString(), LinkedInUser::class.java)
                txtUserName.text = "$firstName $lastName"
                txtHeadline.text = headline
                cardLinkedInUser.setOnClickListener({
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(siteStandardProfileRequest.url))
                    startActivity(browserIntent)
                })
            }

            override fun onApiError(liApiError: LIApiError) {
                toast("Can not retrieve your linked in profile")
            }
        })

    }

    private fun getTwitterUser() {
        val call = MyTwitterApiClient(TwitterCore.getInstance().sessionManager.activeSession)
                .getTimelineService()
                .getUserDetails(TwitterCore.getInstance().sessionManager.activeSession.userId)
        call.enqueue(object : Callback<User>() {
            override fun success(result: Result<User>) {
                with(result.data) {
                    txtName.text = name
                    txtDescription.text = description
                    txtFollowers.text = "$followersCount ${getString(R.string.cnt_followers)}"
                    txtFriendsCount.text = "$friendsCount ${getString(R.string.cnt_friends)}"
                    txtTweets.text = "${status.retweetCount} ${getString(R.string.cnt_retweet)}"
                    imgProfilePic.setRoundedImageOption(profileImageUrlHttps)
                }
            }

            override fun failure(exception: TwitterException) {
                toast(exception.message.toString())
            }
        })
    }

    private fun initTwitterLogin() {
        btnLoginWithTwitter.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                toast("Successfully logged in for ${result.data.userName}")
                isConnectedToAnySocialMedia = true
                isConnectedToTwitter = true
                checkTwitterIsConnected()
            }

            override fun failure(exception: TwitterException) {
                toast(exception.message.toString())
            }
        }
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
}
