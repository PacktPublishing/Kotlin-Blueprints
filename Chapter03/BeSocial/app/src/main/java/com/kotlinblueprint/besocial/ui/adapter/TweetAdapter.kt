package com.kotlinblueprint.besocial.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kotlinblueprint.besocial.R
import com.kotlinblueprint.besocial.extensions.getDateInHours
import com.kotlinblueprint.besocial.extensions.gone
import com.kotlinblueprint.besocial.extensions.visible
import com.kotlinblueprint.besocial.util.setImageLazy
import com.kotlinblueprint.besocial.util.setRoundedImageOption
import com.twitter.sdk.android.core.models.Tweet

/**
 * Created by hardik.trivedi on 18/09/17.
 */
class TweetAdapter(val items: List<Tweet>, val callback: (Tweet) -> Unit) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tweet_list_item, parent, false),
                callback)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showData(items[position])
    }

    class ViewHolder(val view: View, val callback: (Tweet) -> Unit) : RecyclerView.ViewHolder(view) {
        private var imgProfilePic: ImageView
        private var txtName: TextView
        private var txtHandler: TextView
        private var txtTweet: TextView
        private var imgMedia: ImageView
        private var txtTweetTime: TextView

        init {
            imgProfilePic = itemView.findViewById(R.id.imgProfilePic)
            txtName = itemView.findViewById(R.id.txtName)
            txtHandler = itemView.findViewById(R.id.txtHandler)
            txtTweet = itemView.findViewById(R.id.txtTweet)
            imgMedia = itemView.findViewById(R.id.imgMedia)
            txtTweetTime = itemView.findViewById(R.id.txtTweetTime)
        }

        fun showData(tweet: Tweet) {
            with(tweet) {
                imgProfilePic.setRoundedImageOption(user.profileImageUrlHttps)
                txtName.text = user.name
                txtHandler.text = "@${user.screenName}"
                txtTweet.text = text
                txtTweetTime.text = createdAt.getDateInHours()
                if (entities.media.isNotEmpty()) {
                    imgMedia.visible()
                    imgMedia.setImageLazy(entities.media[0].mediaUrlHttps)
                } else {
                    imgMedia.gone()
                }
                itemView.setOnClickListener { callback(this) }
            }
        }
    }
}