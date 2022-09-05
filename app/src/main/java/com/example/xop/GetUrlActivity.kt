package com.example.xop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentActivity

class GetUrlActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_geturl)

        val buttonOpen = findViewById<Button>(R.id.buttonOpen)
        val textUrl = findViewById<EditText>(R.id.editTextTextUri)

        buttonOpen.setOnClickListener { // Code here executes on main thread after user presses button
            val url: String = textUrl.text.toString()
            Log.d(TAG, "URL: $url")
            val video = Video(900, "User Input", "Description", videoUrl = url , "")
            val intent = Intent(this,PlayerActivity::class.java).apply {
                putExtra(PlayerActivity.VIDEO, video)
            }
            startActivity(intent)
        }
    }


    companion object{
        private const val TAG = "OpenurlActivity"
    }
}