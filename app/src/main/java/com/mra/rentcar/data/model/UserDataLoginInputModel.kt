package com.mra.rentcar.data.model

import com.google.gson.annotations.SerializedName

data class UserDataLoginInputModel(
    @SerializedName("user_name")
    val username: String,

    val password: String
)
