package com.dd2d.presentation.my.model

sealed interface MyPageNavigateEvent {
    class CodePost(val id: Int) : MyPageNavigateEvent
    class TIL(val id: Int) : MyPageNavigateEvent
}
