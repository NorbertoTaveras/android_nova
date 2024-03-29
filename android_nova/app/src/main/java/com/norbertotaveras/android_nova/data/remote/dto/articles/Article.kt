package com.norbertotaveras.android_nova.data.remote.dto.articles

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.norbertotaveras.android_nova.data.remote.dto.source.Source
import com.norbertotaveras.android_nova.extensions.displayDateWith
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    @PrimaryKey val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?
) : Parcelable {
    val publishedDate: String?
        get() = publishedAt.displayDateWith()
}