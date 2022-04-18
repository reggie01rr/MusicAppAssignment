package com.example.musicappassignment.fragments.model

import android.os.Parcelable



data class Property(val artistName: String,
                    val trackName: String,
                    val trackPrice: Float,
                    val artworkUrl60: String,
                    val previewUrl: String,
                    val currency: String,
                    val collectionName: String) {

}

data class SongsChannel(
    val resultCount: Int,
    val results:List<Property>
)