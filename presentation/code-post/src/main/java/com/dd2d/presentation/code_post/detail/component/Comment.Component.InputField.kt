package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.core.presentation.main_text.Main700Text

@Composable
internal fun CommentInputFieldComponent(
    nickname: String?,
    content: String,
    onContentChange: (String) -> Unit,
    onCommit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.small
            )
            .padding(12.dp)
    ) {
        nickname?.let {
            Main600Text(
                text = nickname,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
            )
        }
        BasicTextField(
            value = content,
            onValueChange = onContentChange,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
                fontWeight = FontWeight.W400,
            ),
            maxLines = 5,
            decorationBox = { innerTextField ->
                if(content.isBlank()) {
                    Main400Text(
                        text = "악플, 잘못된 정보는 경고없이 삭제될 수 있습니다.",
                        color = MaterialTheme.colorScheme.surfaceBright,
                        fontSize = 12.sp,
                        lineHeight = 16.8.sp,
                    )
                }
                innerTextField()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Main700Text(
            text = "COMMIT",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable(onClick = onCommit)
        )
    }
}