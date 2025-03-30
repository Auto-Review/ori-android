package com.dd2d.presentation.code_post.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.presentation.code_post.content.create.CodePostContentInputContent
import com.dd2d.presentation.code_post.content.create.CodePostInfoInputContent

internal enum class CodePostCreateStep {
    First {
        @Composable
        override fun Content(createState: CodePostCreateState, modifier: Modifier) {
            CodePostInfoInputContent(
                createState = createState,
                modifier = modifier
            )
        }
    },
    Second {
        @Composable
        override fun Content(createState: CodePostCreateState, modifier: Modifier) {
            CodePostContentInputContent(
                createState = createState,
                modifier = modifier
            )
        }
    };

    @Composable
    abstract fun Content(createState: CodePostCreateState, modifier: Modifier)
}