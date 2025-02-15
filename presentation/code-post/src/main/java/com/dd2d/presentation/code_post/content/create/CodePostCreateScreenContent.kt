package com.dd2d.presentation.code_post.content.create

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.main_button.MainButton
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text_field.MainTextFieldDefaults
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.domain.code_post.model.Code
import com.dd2d.domain.code_post.model.CodePostCreator
import com.dd2d.presentation.code_post.component.CodeLevelSlider
import com.dd2d.presentation.code_post.component.detail.CodeComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
internal fun CodePostCreateScreenContent(
    onSave: (creator: CodePostCreator) -> Unit,
    isSaving: Boolean,
    modifier: Modifier = Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    var title by remember { mutableStateOf("") }
    var code by remember { mutableStateOf(Code(language = "Kotlin", content = "")) }
    var description by remember  { mutableStateOf("") }
    var level by remember { mutableIntStateOf(0) }
    var reviewDate by remember { mutableStateOf<LocalDateTime?>(null) }


    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxSize().imePadding()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .verticalScroll(state = scrollState)
                    .padding(24.dp)
            ){
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Main400Text(text = "제목을 입력해주세요", color = Color.LightGray) },
                    colors = MainTextFieldDefaults.mainTextFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )
                Box(modifier = Modifier.padding(vertical = 10.dp)) {
                    CodeLevelSlider(level = level, onLevelChange = { level = it })
                }
                CodeComponent(
                    code = code,
                    onCodeChange = { code = it },
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Main400Text(text = "내용을 입력해주세요", color = Color.LightGray) },
                    colors = MainTextFieldDefaults.mainTextFieldColors(),
                    minLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            MainButton(
                text = "저장하기",
                isLoading = isSaving,
                enabled = validateCodePost(title, code, description)
            ) {
                keyboard?.hide()
                onSave(
                    CodePostCreator(
                        title = title,
                        code = code,
                        level = level,
                        description = description,
                        reviewDate = reviewDate
                    )
                )
            }
        }
    }
}

private fun validateCodePost(
    title: String,
    code: Code,
    description: String
): Boolean {
    return title.isNotBlank()
            && code.language.isNotBlank()
            && code.content.isNotBlank()
            && description.isNotBlank()
}

@Preview
@Preview(locale = "ko", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CodePostCreateScreenContentPrev() {

    val s = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    AppTheme {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CodePostCreateScreenContent(
                onSave = {
                    s.launch {
                        isLoading = true
                        delay(1000)
                        isLoading = false
                    }
                },
                isSaving = isLoading,
                modifier = Modifier
            )
        }
    }
}