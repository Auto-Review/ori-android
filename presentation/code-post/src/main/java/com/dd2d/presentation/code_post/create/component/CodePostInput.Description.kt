package com.dd2d.presentation.code_post.create.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation._ori.InputItem

@Composable
internal fun CodePostDescriptionInput(
    descriptionTextState: TextFieldState,
    modifier: Modifier = Modifier
) {
    InputItem(label = "Description", modifier = modifier) {
        BasicTextField(
            state = descriptionTextState,
            lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 5),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.small)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.small
                )
                .padding(14.dp)
        )
    }
}