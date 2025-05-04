package com.dd2d.domain.notification.model

import com.dd2d.core.core.model.DateString
import com.dd2d.core.core.util.format
import java.time.LocalDate

data class Notification(
    val id: Int,
    val codePostId: Int,
    val content: String,
    val noticeAt: DateString,
    val checked: Boolean,
    val state: State
) {
    enum class State {
        PENDING, COMPLETE
    }

    companion object {
        fun dummy(id: Int = 1) = Notification(
            id = id,
            codePostId = id,
            content = "알림 내용 $id",
            noticeAt = LocalDate.now().format("yyyy-MM-dd"),
            checked = true,
            state = State.entries.random(),
        )
    }
}
