package ru.bilenkod.retrofitexample

import com.google.gson.annotations.SerializedName

class Post {
    var id: Int = 0
    var userId: Int = 0
    var body: String? = null
    @SerializedName("title")
    var name: String? = null
}