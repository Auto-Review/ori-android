package com.dd2d.presentation.my.model

sealed interface MyPageNavigateEvent {
    data object CodePostCreate : MyPageNavigateEvent
    data object TILPostCreate : MyPageNavigateEvent
    class CodePost(val id: Int) : MyPageNavigateEvent
    class TIL(val id: Int) : MyPageNavigateEvent
}
