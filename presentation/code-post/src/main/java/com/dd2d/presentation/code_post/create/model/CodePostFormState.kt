package com.dd2d.presentation.code_post.create.model

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dd2d.domain.code_post.model.post.Code
import com.dd2d.domain.code_post.model.post.CodePostCreator
import com.dd2d.domain.code_post.model.post.CodePostUpdater
import java.time.LocalDateTime

internal class CodePostFormState {
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
    var language by mutableStateOf<Code.Language?>(null)
    var isPublic by mutableStateOf(true)
    var reviewDate by mutableStateOf<LocalDateTime?>(null)
    var descriptionTextState = TextFieldState()
    var codeTextState = TextFieldState()

    val canSubmit by derivedStateOf {
        title.isNotBlank()
                && level > 0
                && language != null
                && descriptionTextState.text.isNotBlank()
                && codeTextState.text.isNotBlank()
    }

    var isSubmitting by mutableStateOf(false)

    fun toCodePostCreator(): CodePostCreator {
        return CodePostCreator(
            title = title,
            level = level,
            code = Code(
                language = language!!,
                content = codeTextState.text.toString()
            ),
            description = descriptionTextState.text.toString(),
            isPublic = isPublic,
            reviewDate = reviewDate,
        )
    }

    fun toCodePostUpdater(id: Int): CodePostUpdater {
        return CodePostUpdater(
            id = id,
            title = title,
            code = Code(
                language = language,
                content = codeTextState.text.toString(),
            ),
            description = descriptionTextState.text.toString(),
            level = level,
            isPublic = isPublic,
            reviewDate = reviewDate,
        )
    }
}