package com.example.presentation.til.create.model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.domain.til.model.TILCreator
import java.time.LocalDateTime

internal class TILCreateState {
    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var isPrivate by mutableStateOf(false)
    var reviewDate by mutableStateOf<LocalDateTime?>(null)

    val canCreate by derivedStateOf {
        title.isNotBlank() && content.isNotBlank()
    }

    fun toTILCreator(): TILCreator {
        return TILCreator(
            title = title,
            content = content,
        )
    }
}