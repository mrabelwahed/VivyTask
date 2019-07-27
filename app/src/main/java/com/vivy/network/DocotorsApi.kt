package com.vivy.network

import com.vivy.data.model.DoctorsResponse
import io.reactivex.Observable
import retrofit2.http.*


interface DocotorsApi {
        @GET("/api/users/me/doctors")
        fun searchDoctors(@HeaderMap headers:Map<String,String>,@Query("search") searchKey: String?, @Query("lat") lat: Double, @Query("lng") lng: Double, @Query("lastKey") lastKey: String?): Observable<DoctorsResponse>
}