package com.example.xop

import java.io.Serializable

data class Video(var id: Long = 0,
                 var title: String? = null,
                 var description: String? = null,
                 var videoUrl: String? = null,
                 var cardImageUrl: String? = null,
                 ) : Serializable {
    override fun toString(): String {
        return "VideoStream{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", cardImageUrl='" + cardImageUrl + '\'' +
                '}'
    }

    companion object {
        internal const val serialVersionUID = 727566175075960653L
    }
}
