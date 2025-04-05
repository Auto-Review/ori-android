package com.dd2d.presentation.code_post.detail.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.domain.code_post.model.post.CodePost
import com.dd2d.presentation.code_post.detail.component.CodeComponent

@Composable
internal fun CodePostScreenContent(
    codePost: CodePost,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(24.dp)
        ){
            Main700Text(
                text = codePost.title,
                fontSize = 18.sp,
            )
            Main400Text(
                text = codePost.createdAt + " · " + codePost.author.nickname,
                fontSize = 12.sp
            )
            CodeComponent(
                code = codePost.code,
                onCodeChange = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            Main400Text(
                text = codePost.description,
                maxLine = 5,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Preview(locale = "ko")
@Composable
private fun CodePostScreenContentPrev() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CodePostScreenContent(
            codePost = CodePost.dummy(),
            modifier = Modifier
        )
    }
}