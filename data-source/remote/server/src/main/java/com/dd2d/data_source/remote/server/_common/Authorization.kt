package com.dd2d.data_source.remote.server._common

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders


internal fun HttpRequestBuilder.authorizationHeader(token: String) {
    headers {
        append(HttpHeaders.Authorization, "Bearer $token")
    }
}
