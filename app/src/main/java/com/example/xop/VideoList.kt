package com.example.xop

object VideoList {

    val dash: List<Video> by lazy {
        setupChannels()
    }

    private var count: Long = 0

    private fun setupChannels(): List<Video> {
        val title = arrayOf(
            "Dialog Test Live 203",
            "HD (MP4, H264)",
            "HD (MP4, H265)",
            "UHD (MP4, H265)",
            "Introducing Google Nose"
        )

        val description = arrayOf(
            "default_card_image",
            "default_card_image",
            "default_card_image",
            "default_card_image",
            "default_card_image"
        )

        val videoUrl = arrayOf(
            "https://bpcdn.dialog.lk/bpk-tv/Ch203/output/index.mpd",
            "https://storage.googleapis.com/wvmedia/clear/h264/tears/tears.mpd",
            "https://storage.googleapis.com/wvmedia/clear/hevc/tears/tears.mpd",
            "https://storage.googleapis.com/wvmedia/clear/hevc/tears/tears_uhd.mpd",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4"
        )

        val cardImageUrl = arrayOf(
            "https://www.dialog.lk/dialogdocroot/content/images/channel-highlights/channelone.png",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg"
        )

        return title.indices.map {
            buildVideoInfo(
                title[it],
                description[it],
                videoUrl[it],
                cardImageUrl[it]
            )
        }
    }


    private fun buildVideoInfo(
        title: String,
        description: String,
        videoUrl: String,
        cardImageUrl: String
    ): Video {
        val video = Video()
        video.id = count++
        video.title = title
        video.description = description
        video.videoUrl = videoUrl
        video.cardImageUrl = cardImageUrl
        return video
    }
}