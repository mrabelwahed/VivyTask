package com.vivy.repository

import com.vivy.data.model.AuthTokenResponse
import com.vivy.data.model.DoctorsResponse
import com.vivy.network.AuthApi
import com.vivy.network.DocotorsApi
import io.reactivex.Observable
import java.util.*

class DoctorsRepository(private val authApi: AuthApi, private val doctorsApi: DocotorsApi) {

    companion object {
        var inMemoryToken: String = ""
    }


    fun getAuthToken(): Observable<AuthTokenResponse> {
        return authApi.getAuthToken(getBasicHeaders(), "androidChallenge@vivy.com", "88888888")
    }


    fun loadDoctorsList(searchKey: String?, lat: Double, lng: Double, lastKey: String?): Observable<DoctorsResponse> {
        return doctorsApi.searchDoctors(getHeaderWithToken(), searchKey, lat, lng, lastKey)
    }

    private fun getBasicHeaders(): Map<String, String> {
        var headers = HashMap<String, String>()
        headers["Authorization"] = "Basic aXBob25lOmlwaG9uZXdpbGxub3RiZXRoZXJlYW55bW9yZQ=="
        headers["Accept"] = "application/json"
        headers["Content-Type"] = "application/x-www-form-urlencoded"
        return headers
    }
    private fun getHeaderWithToken(): Map<String, String> {
        var headers = HashMap<String, String>()
        headers["Authorization"] = "Bearer $inMemoryToken"
        headers["Accept"] = "application/json"
        headers["Content-Type"] = "application/x-www-form-urlencoded"
        return headers
    }

}