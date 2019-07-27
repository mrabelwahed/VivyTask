package com.vivy.data.model

data class AuthTokenResponse(
    val access_token: String,
    val expires_in: Int,
    val jti: String,
    val phoneVerified: Boolean,
    val refresh_token: String,
    val scope: String,
    val token_type: String
)