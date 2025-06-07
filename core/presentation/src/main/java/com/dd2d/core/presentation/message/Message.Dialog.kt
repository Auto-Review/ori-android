package com.dd2d.core.presentation.message

import androidx.compose.ui.graphics.Color
import com.dd2d.core.presentation.theme.MainColor

interface DialogMessageField {
  val title: String?
  val content: String?
  val actions: List<Message.Dialog.Action>
}

@DslMarker
private annotation class DialogMessageScopeDsl

@DialogMessageScopeDsl
interface DialogMessageScope : DialogMessageField {
  override var title: String?
  override var content: String?
  override var actions: MutableList<Message.Dialog.Action>
  fun addAction(action: Message.Dialog.Action)
  fun addAction(scope: DialogMessageActionScope.() -> Unit)
}

@DialogMessageScopeDsl
interface DialogMessageActionScope {
  var text: String
  var onClick: (dismissRequest: () -> Unit) -> Unit
  var textColor: Color
  var backgroundColor: Color
  var ratio: Float
}


internal class DialogMessageScopeImpl : DialogMessageScope {
  override var title: String? = null
  override var content: String? = null
  override var actions: MutableList<Message.Dialog.Action> = mutableListOf()

  override fun addAction(action: Message.Dialog.Action) {
    actions.add(action)
  }

  override fun addAction(scope: DialogMessageActionScope.() -> Unit) {
    val buildScope = DialogMessageActionScopeImpl().apply(scope)
    actions.add(
      Message.Dialog.Action(
        text = buildScope.text,
        onClick = { dismissRequest -> buildScope.onClick(dismissRequest) },
        textColor = buildScope.textColor,
        backgroundColor = buildScope.backgroundColor,
        ratio = buildScope.ratio,
      )
    )
  }
}

internal class DialogMessageActionScopeImpl : DialogMessageActionScope {
  override var text: String = ""
  override var onClick: (dismissRequest: () -> Unit) -> Unit = { it() }
  override var textColor: Color = MainColor
  override var backgroundColor: Color = Color.Transparent
  override var ratio: Float = 1F
}
