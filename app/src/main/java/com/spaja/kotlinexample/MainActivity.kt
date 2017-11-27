package com.spaja.kotlinexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.spaja.kotlinexample.model.ApiResponse
import com.spaja.kotlinexample.networking.GiphyAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input: EditText = findViewById(R.id.input)
        initializeRecyclerView()

        input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val j = 0
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val i = 0
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getGifs(p0.toString())
            }
        })
    }

    private fun initializeRecyclerView() {
        recycler = findViewById(R.id.recycler)
        val linearLayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = linearLayoutManager
    }

    private fun getGifs(input: String) {
        GiphyAPI.create().getGifs("yXeFI6D8TyVNtjLVYv17riJr0Or4kp23", input).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                recycler.adapter = GifsRecyclerAdapter(response.body()!!.gifData)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
