package com.dd2d.presentation.code_post.create.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.presentation.code_post.create.content.CodePostContentInputContent
import com.dd2d.presentation.code_post.create.content.CodePostInfoInputContent

internal enum class CodePostCreateStep {
    First {
        @Composable
        override fun Content(createState: CodePostFormState, modifier: Modifier) {
            CodePostInfoInputContent(
                createState = createState,
                modifier = modifier
            )
        }
    },
    Second {
        @Composable
        override fun Content(createState: CodePostFormState, modifier: Modifier) {
            CodePostContentInputContent(
                createState = createState,
                modifier = modifier
            )
        }
    };

    @Composable
    abstract fun Content(createState: CodePostFormState, modifier: Modifier)
}