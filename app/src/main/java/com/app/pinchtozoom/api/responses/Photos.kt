package com.app.pinchtozoom.api.responses

import com.google.gson.annotations.SerializedName

class Photos(
    @SerializedName("albumId")
    private var albumId: Int,
    @SerializedName("id")
    private var id: Int,
    @SerializedName("title")
    private var title: String,
    @SerializedName("url")
    private var url: String,
    @SerializedName("thumbnailUrl")
    private var thumbnailUrl: String
)