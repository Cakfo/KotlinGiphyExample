package com.spaja.kotlinexample.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Spaja on 27-Nov-17.
 */
data class FixedWidth(
        @SerializedName("url")
        val url: String)
