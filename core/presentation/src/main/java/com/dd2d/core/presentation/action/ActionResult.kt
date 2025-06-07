package com.dd2d.core.presentation.action

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


interface ActionResult

interface ActionResultBus {
  val actionResult: SharedFlow<ActionResult>
  suspend fun collectResult(collector: FlowCollector<ActionResult>)
  suspend fun newResult(result: ActionResult)
}

class CommonActionResultBus : ActionResultBus {
  private val _actionResult = MutableSharedFlow<ActionResult>()
  override val actionResult = _actionResult.asSharedFlow()
  override suspend fun collectResult(collector: FlowCollector<ActionResult>) {
    actionResult.collect(collector)
  }

  override suspend fun newResult(result: ActionResult) {
    _actionResult.emit(result)
  }

  suspend fun emitSuccess() {
    _actionResult.emit(CommonActionResult.ActionSuccess)
  }

  suspend fun emitFailure(exception: Throwable) {
    _actionResult.emit(CommonActionResult.ActionFailure(exception))
  }
}

sealed interface CommonActionResult : ActionResult {
  data object ActionSuccess : CommonActionResult
  class ActionFailure(val exception: Throwable) : CommonActionResult {
    override fun toString(): String = "ActionFailure -> ${exception.message}"
  }
}