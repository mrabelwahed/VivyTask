package com.bailm.vivychallenge.util

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.vivy.repository.DoctorsRepository

object GlideUtils {

    fun buildUrlWithHeaders(url: String): GlideUrl {
        return GlideUrl(
            url, LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer ${DoctorsRepository.inMemoryToken}")
                .build()
        )
    }
}