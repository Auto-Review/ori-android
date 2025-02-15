package com.dd2d.core.presentation.main_button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main500Text
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.core.presentation.theme.AppTheme

@Composable
fun MainButton(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth().height(54.dp),
    shape: Shape = RectangleShape,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    colors: MainButtonColors = MainButtonDefaults.mainButtonColors(),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.containerColor,
            contentColor = colors.contentColor,
            disabledContainerColor = if(isLoading) colors.loadingContainerColor else colors.disabledContainerColor,
            disabledContentColor = if(isLoading) colors.loadingContentColor else colors.disabledContentColor
        ),
        enabled = enabled && !isLoading,
        modifier = modifier
    ) {
        if(isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 3.dp,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
                strokeCap = StrokeCap.Round,
                modifier = Modifier.fillMaxHeight(0.5F).aspectRatio(1F)
            )
        }
        else {
            Main600Text(
                text = text,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Preview(locale = "ko")
@Composable
private fun MainButtonPrev() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            MainButton(
                text = "테스트",
                isLoading = false,
                onClick = {},
            )
            MainButton(
                text = "테스트",
                isLoading = false,
                enabled = false,
                onClick = {},
            )
            MainButton(
                text = "테스트",
                isLoading = true,
                onClick = {},
            )
        }
    }
}