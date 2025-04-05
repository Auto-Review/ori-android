package com.dd2d.domain.til.model

import com.dd2d.core.core.model.DateString
import java.time.LocalDate

data class TILListItem(
    val id: Int,
    val title: String,
    val content: String,
    val author: TILAuthor,
    val createdAt: DateString,
) {
    companion object {
        fun dummy(id: Int = 1) = TILListItem(
            id = id,
            title = "${id}번 째 TIL 제목",
            content = "${id}번 째 TIL 내용",
            author = TILAuthor.dummy(),
            createdAt = LocalDate.now().toString(),
        )
    }
}
