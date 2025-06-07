package com.dd2d.data.til.mapper

import com.dd2d.data_source.remote.server.til.dto.request.TILCreateRequestDto
import com.dd2d.domain.til.model.TILCreator

internal fun TILCreator.toTILCreateRequestDto(): TILCreateRequestDto {
  return TILCreateRequestDto(
    title = this.title,
    content = this.content,
  )
}