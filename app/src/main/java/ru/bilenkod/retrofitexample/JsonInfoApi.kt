package ru.bilenkod.retrofitexample

import retrofit2.Call
import retrofit2.http.GET

interface JsonInfoApi {
    @get:GET("posts")
    val posts: Call<List<Post>>
}