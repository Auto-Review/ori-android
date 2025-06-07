package com.dd2d.core.core.exception


open class ManagedException(
  override val message: String,
  override val cause: Throwable?
) : Exception()