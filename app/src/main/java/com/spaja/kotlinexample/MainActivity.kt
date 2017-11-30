package com.spaja.kotlinexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.spaja.kotlinexample.model.ApiResponse
import com.spaja.kotlinexample.networking.GiphyAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input: EditText = findViewById(R.id.input)
        initializeRecyclerView()

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val client = GoogleSignIn.getClient(this, gso)
        val signIn: SignInButton = findViewById(R.id.sign_in_button)

        signIn.setOnClickListener {
            val i: Intent = client.signInIntent
            startActivityForResult(i, RC_SIGN_IN)
        }

        input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
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
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (task.isSuccessful) {
                val acct = task.result
                Toast.makeText(this, acct.displayName, Toast.LENGTH_LONG).show()
            } else {
                // Sign in failed, handle failure and update UI
                // ...
            }
        }
    }
}
