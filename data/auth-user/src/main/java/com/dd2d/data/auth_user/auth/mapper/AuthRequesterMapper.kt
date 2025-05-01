package com.dd2d.data.auth_user.auth.mapper

import com.dd2d.data_source.remote.server.auth.dto.request.AuthRequestDto
import com.dd2d.domain.auth_user.auth.model.AuthRequester

internal fun AuthRequester.toAuthRequestDto(): AuthRequestDto {
    return AuthRequestDto(oAuthToken = oAuthToken)
}