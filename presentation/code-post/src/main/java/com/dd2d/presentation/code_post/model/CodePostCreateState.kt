package com.dd2d.presentation.code_post.model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.domain.code_post.model.post.Code
import com.dd2d.domain.code_post.model.post.CodePostCreator
import java.time.LocalDateTime

internal class CodePostCreateState {
    var step by mutableStateOf(CodePostCreateStep.First)
        private set
    fun prevStep() {
        if(step == CodePostCreateStep.entries.first()) {
            return
        }

        step = CodePostCreateStep.entries[step.ordinal - 1]
    }
    fun nextStep() {
        if(step == CodePostCreateStep.entries.last()) {
            return
        }

        step = CodePostCreateStep.entries[step.ordinal + 1]
    }

    var title by mutableStateOf("")
    val maxLevel = 5
    var level by mutableIntStateOf(0)
    var language by mutableStateOf("")
    var isPrivate by mutableStateOf(false)
    var reviewDate by mutableStateOf<LocalDateTime?>(null)
    var description by mutableStateOf("")
    var code by mutableStateOf("")

    val canCreate by derivedStateOf {
        title.isNotBlank()
                && level > 0
                && language.isNotBlank()
                && description.isNotBlank()
                && code.isNotBlank()
    }

    fun toCodePostCreator(): CodePostCreator {
        return CodePostCreator(
            title = title,
            level = level,
            code = Code(
                language = language,
                content = code
            ),
            description = description,
            reviewDate = reviewDate,
        )
    }
}