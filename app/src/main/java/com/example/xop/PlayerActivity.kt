package com.example.xop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity

class PlayerActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val (_, title, videoUrl, _) = this.intent?.getSerializableExtra(VIDEO) as Video
        Log.d(TAG, "onCreate with companion object $videoUrl")
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, PlayerFragment())
                .commit()
        }

    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }


    companion object{
        private const val TAG = "PlayerActivity"
        const val VIDEO = "Video"
    }

}