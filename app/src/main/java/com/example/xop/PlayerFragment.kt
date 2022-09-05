package com.example.xop

import android.os.Bundle
import android.util.Log
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.session.MediaSession
import androidx.media3.ui.leanback.LeanbackPlayerAdapter


class PlayerFragment: VideoSupportFragment() {

    private lateinit var mediaSession: MediaSession
    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<LeanbackPlayerAdapter>
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        val (_, title,_,videoUrl, _) =
            activity?.intent?.getSerializableExtra(PlayerActivity.VIDEO) as Video

        val glueHost = VideoSupportFragmentGlueHost(this@PlayerFragment)

        val videoProtocol: String? = videoUrl?.substringBefore(":")
        Log.d(TAG, "videoTitle: $title, videoProtocol: $videoProtocol , videoUrl: $videoUrl")

        //Exoplayer
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                videoUrl?.let { MediaItem.fromUri(it) }?.let { exoPlayer.setMediaItem(it) }
                exoPlayer.playWhenReady = true
                exoPlayer.seekTo(0, 0L)
                exoPlayer.addAnalyticsListener(EventLogger())
                exoPlayer.prepare()
            }


        //Media session
        mediaSession = context?.let { MediaSession.Builder(it, player).build() }!!

        // Links player with this Leanback video playback fragment
        val playerAdapter = LeanbackPlayerAdapter(
            requireContext(), player, PLAYER_UPDATE_INTERVAL_MILLIS)

        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        mTransportControlGlue = PlaybackTransportControlGlue(activity, playerAdapter)
        mTransportControlGlue.host = glueHost
        mTransportControlGlue.title = title
        //mTransportControlGlue.playWhenPrepared()

    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
        player.release()
        mediaSession.release()
        mTransportControlGlue.pause()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onDestroy() {
        Log.d(TAG, "onCreate")
        super.onDestroy()
        mediaSession.release()
    }


    companion object {
        private val TAG = PlayerFragment::class.java.simpleName
        private const val PLAYER_UPDATE_INTERVAL_MILLIS: Int = 100
    }
}

