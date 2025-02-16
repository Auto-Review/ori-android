package com.dd2d.data.auth.mapper

import com.dd2d.data_source.remote.server.auth.dto.request.AuthRequestDto
import com.dd2d.domain.auth.model.AuthRequester

internal fun AuthRequester.toAuthRequestDto(): AuthRequestDto {
    return AuthRequestDto(oAuthToken = oAuthToken)
}