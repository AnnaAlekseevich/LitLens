package com.example.litlens.data.remote.google

import retrofit2.http.GET
import retrofit2.http.Query
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

interface GoogleBooksApi {
    @GET("/books/v1/volumes")
    suspend fun searchVolumes(
        @Query("q") query: String,
        @Query("key") apiKey: String = "AIzaSyDy0INSV8S7sd47q-ZOL9ufdw5Lo_Bvr0w",
        @Query("maxResults") max: Int = 5
    ): GoogleBooksResponse
}

@JsonClass(generateAdapter = true)
data class GoogleBooksResponse(
    @Json(name = "items")
    val items: List<BookItem>?
)

@JsonClass(generateAdapter = true)
data class BookItem(
    @Json(name = "id")
    val id: String?,
    @Json(name = "volumeInfo")
    val volumeInfo: VolumeInfo?
)

@JsonClass(generateAdapter = true)
data class VolumeInfo(
    @Json(name = "title")
    val title: String?,
    @Json(name = "subtitle")
    val subtitle: String?,
    @Json(name = "authors")
    val authors: List<String>?,
    @Json(name = "publisher")
    val publisher: String?,
    @Json(name = "publishedDate")
    val publishedDate: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "pageCount")
    val pageCount: Int?,
    @Json(name = "categories")
    val categories: List<String>?,
    @Json(name = "imageLinks")
    val imageLinks: ImageLinks?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "previewLink")
    val previewLink: String?,
    @Json(name = "infoLink")
    val infoLink: String?,
    @Json(name = "canonicalVolumeLink")
    val canonicalVolumeLink: String?
)

@JsonClass(generateAdapter = true)
data class ImageLinks(
    @Json(name = "smallThumbnail")
    val smallThumbnail: String?,
    @Json(name = "thumbnail")
    val thumbnail: String?
)