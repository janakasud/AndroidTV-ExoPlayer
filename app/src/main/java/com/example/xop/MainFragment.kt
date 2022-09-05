package com.example.xop

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*

class MainFragment: BrowseSupportFragment(), OnItemViewClickedListener, OnItemViewSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "setupUIElements")
        title = "EXO Player"
        headersState = HEADERS_ENABLED

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")
        loadUI()
        onItemViewClickedListener = this
        onItemViewSelectedListener = this

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    private fun loadUI() {
        Log.d(TAG, "loadRows")

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()

        //Dash
        val dashList = VideoList.dash
        val dashHeader = HeaderItem(0, "DASH")
        val dashRowAdapter = ArrayObjectAdapter(cardPresenter)
        for (j in dashList.indices) {
            dashRowAdapter.add(dashList[j % dashList.size])
        }
        rowsAdapter.add(ListRow(dashHeader, dashRowAdapter))

        //Udp
        val udpHeader = HeaderItem(1, "UDP")
        val udpRowAdapter = ArrayObjectAdapter(cardPresenter)

        val udpUnicast = Video(0, "UDP Unicast", "udp://0.0.0.0:5000", "udp://0.0.0.0:5000", R.drawable.udp_uni.toString())
        udpRowAdapter.add(udpUnicast)
        val udpMulticast= Video(0, "UDP Multicast", "udp://239.1.1.1:5000", "udp://239.1.1.1:5000", R.drawable.udp_multi.toString())
        udpRowAdapter.add(udpMulticast)
        rowsAdapter.add(ListRow(udpHeader, udpRowAdapter))

        //Url
        val openUrlHeader = HeaderItem(2, "URL")
        val openUrlRowAdapter = ArrayObjectAdapter(cardPresenter)
        openUrlRowAdapter.add(Video(999, "URL", "Enter a URL", null, R.drawable.get_url.toString()))
        rowsAdapter.add(ListRow(openUrlHeader, openUrlRowAdapter))

        //About
        val textHeader = HeaderItem(3, "About")
        val textRowAdapter = ArrayObjectAdapter(TextItemPresenter())
        textRowAdapter.add(
            "ExoPlayer for testing purpose." + "\n" + "V0.1" +"\n"+"2022-10-04")
        rowsAdapter.add(ListRow(textHeader, textRowAdapter))

        adapter = rowsAdapter

    }

    override fun onItemClicked(
        itemViewHolder: Presenter.ViewHolder?,
        item: Any?,
        rowViewHolder: RowPresenter.ViewHolder?,
        row: Row?
    ) {
        Log.d(TAG, "ItemViewClickedListener")
        if(item != null) {
            if (item is Video) {
                if(item.id == 999L){
                    Log.d(TAG, "onItemClicked: $item")
                    val intent = Intent(requireContext(), GetUrlActivity::class.java)
                    startActivity(intent)

                }else {
                    Log.d(TAG, "onItemClicked: $item")
                    val intent = Intent(requireContext(), PlayerActivity::class.java)
                    intent.putExtra(PlayerActivity.VIDEO, item)

                    startActivity(intent)
                }
            }
        }else {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemSelected(
        itemViewHolder: Presenter.ViewHolder?,
        item: Any?,
        rowViewHolder: RowPresenter.ViewHolder?,
        row: Row?
    ) {
        Log.d(TAG, "onItemSelected : " + item.toString() + "Row:" + row.toString())
    }

    private inner class TextItemPresenter : Presenter() {
        override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
            val view = TextView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(313, 171)
            view.isFocusable = false
            view.isFocusableInTouchMode = false
            view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.default_background))
            view.setTextColor(Color.WHITE)
            view.gravity = Gravity.CENTER
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
            (viewHolder.view as TextView).text = item as String
        }

        override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
    }

    companion object{
        private const val TAG = "MainFragment"
    }
}