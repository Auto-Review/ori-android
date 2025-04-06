package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.domain.code_post.model.post.Code
import com.dd2d.presentation.code_post.R

@Composable
internal fun CodeComponent(
    code: Code,
    modifier: Modifier = Modifier
) {
    val clipboard = LocalClipboardManager.current
    Column(
        modifier = modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.outlineVariant, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Main400Text(
                text = code.language,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                lineHeight = 16.8.sp
            )
            VectorIconButton(
                res = R.drawable.copy,
                iconSize = 16.dp
            ) {
                clipboard.setText(
                    AnnotatedString(code.content)
                )
            }
        }
        Text(
            text = code.content,
            color = Color.LightGray,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}