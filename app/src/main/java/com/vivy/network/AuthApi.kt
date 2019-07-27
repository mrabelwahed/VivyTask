package com.vivy.network

import com.vivy.data.model.AuthTokenResponse
import io.reactivex.Observable
import retrofit2.http.*


interface AuthApi {
    @FormUrlEncoded
    @POST("oauth/token?grant_type=password")
    fun getAuthToken(@HeaderMap headers: Map<String, String>,
                   @Field("username") username:String,
                   @Field("password") password:String): Observable<AuthTokenResponse>
}