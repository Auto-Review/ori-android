package com.dd2d.presentation.code_post.create.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.core.presentation._ori.InputItem
import com.dd2d.core.presentation.main_text_field.MainTextFieldDefaults

@Composable
internal fun CodePostDescriptionInput(
    description: String,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    InputItem(label = "Description", modifier = modifier) {
        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            minLines = 5,
            shape = MaterialTheme.shapes.small,
            colors = MainTextFieldDefaults.outlineTextFieldColors(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}