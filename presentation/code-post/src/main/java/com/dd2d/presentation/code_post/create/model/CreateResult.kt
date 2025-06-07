package com.dd2d.presentation.code_post.create.model

import com.dd2d.core.presentation.action.ActionResult

internal data class CodePostFormActionCompleteResult(
  val codePostId: Int?
) : ActionResult