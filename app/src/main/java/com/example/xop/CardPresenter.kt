package com.example.xop

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide


class CardPresenter: Presenter() {

    private var mDefaultCardImage: Drawable? = null

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        Log.d(TAG, "onCreateViewHolder")

        mDefaultCardImage = ContextCompat.getDrawable(parent!!.context, R.drawable.default_background)


        val cardView = object : ImageCardView(parent.context) {
        }

        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        //updateCardBackgroundColor(cardView, false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val video = item as Video
        val cardView = viewHolder?.view as ImageCardView

        Log.d(TAG, "onBindViewHolder")

        cardView.titleText = video.title
        cardView.contentText = video.description
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)

        var cardImageUrlType: String? = video.cardImageUrl?.substringBefore(":")
        Log.d(TAG, "cardImageUrlType: $cardImageUrlType")
        /*
        if (video.cardImageUrl != null) {
            Glide.with(viewHolder.view.context)
                .load(video.cardImageUrl)
                .centerCrop()
                .error(mDefaultCardImage)
                .into(cardView.mainImageView)
        }

         */
        if(cardImageUrlType == "http" || cardImageUrlType == "https"){
            Glide.with(viewHolder.view.context)
                .load(video.cardImageUrl)
                .centerCrop()
                .error(mDefaultCardImage)
                .into(cardView.mainImageView)
        }else{
            Glide.with(viewHolder.view.context)
                .load(video.cardImageUrl!!.toInt())
                .centerCrop()
                .error(mDefaultCardImage)
                .into(cardView.mainImageView)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        Log.d(TAG, "onUnbindViewHolder")
        val cardView = viewHolder?.view as ImageCardView
        // Remove references to images so that the garbage collector can free up memory
        cardView.badgeImage = null
        cardView.mainImage = null
    }

    companion object{
        private val TAG = "CardPresenter"

        private val CARD_WIDTH = 313
        private val CARD_HEIGHT = 176
    }

}