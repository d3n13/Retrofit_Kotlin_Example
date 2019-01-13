package ru.bilenkod.retrofitexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.textView_result)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonInfoApi = retrofit.create(JsonInfoApi::class.java)
        val posts = jsonInfoApi.posts
        posts.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    result.text = String.format(Locale.getDefault(), "Code: %d", response.code())
                    return
                }
                val postList = response.body()
                val content = StringBuilder()
                if (postList != null) {
                    for (post in postList) {
                        content.append("ID: ").append(post.id).append("\n")
                        content.append("User ID: ").append(post.userId).append("\n")
                        content.append("Name: ").append(post.name).append("\n")
                        content.append("Content: ").append(post.body).append("\n\n")
                    }
                }
                result.text = content.toString()
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }
        })
    }

}
