package com.dd2d.core.presentation_oauth.google.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
internal fun AuthComponent(
    text: @Composable BoxScope.() -> Unit,
    color: Color,
    modifier: Modifier = Modifier,
    icon: @Composable BoxScope.() -> Unit = {},
    loadingIndicator: @Composable BoxScope.() -> Unit = {},
    isLoading: Boolean = false,
    shape: Shape = RectangleShape,
    borderStroke: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(vertical = 20.dp),
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = color,
        shape = shape,
        border = borderStroke,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                content = icon,
                modifier = Modifier
                    .weight(0.1F)
            )
            Box(
                contentAlignment = Alignment.Center,
                content = text,
                modifier = Modifier.weight(1F)
            )
            Box(
                contentAlignment = Alignment.Center,
                content = {
                    if(isLoading) {
                        loadingIndicator()
                    }
                },
                modifier = Modifier
                    .weight(0.1F)
            )
        }
    }
}

@Composable
internal fun BoxScope.DefaultLoadingIndicator () {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
        strokeCap = StrokeCap.Round,
        strokeWidth = 2.dp,
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .size(20.dp)
    )
}

@Preview
@Preview(locale = "ko")
@Composable
private fun AuthComponentPrev() {
    var isLoading by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
    ) {
        AuthComponent(
//            icon = {
//                Icon(Icons.Default.Add, null, modifier = Modifier.align(Alignment.CenterStart))
//            },
            text = {
                Text(text = "sign in with google")
            },
            isLoading = isLoading,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp),
            color = Color.White,
            loadingIndicator = {},
            onClick = {
                isLoading = !isLoading
            },
            modifier = Modifier
        )
    }
}