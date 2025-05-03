package com.dd2d.presentation.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.image.PainterImage
import com.dd2d.core.presentation.theme.HansFontFamily
import com.dd2d.core.presentation.theme.tp
import com.dd2d.presentation.schedule.R

@Composable
internal fun BannerComponent(
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.onSurface
) {
    Surface(
        color = background,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            PainterImage(res = R.drawable.banner, modifier = Modifier.align(Alignment.CenterStart))
            Text(
                text = buildAnnotatedString {
                    val style1 = SpanStyle(color = Color(0xFF424242), fontSize = 18.tp, fontWeight = FontWeight.W400, fontFamily = HansFontFamily)
                    val style2 = SpanStyle(color = Color(0xFFF4F4F4), fontSize = 18.tp, fontWeight = FontWeight.W400, fontFamily = HansFontFamily)
                    withStyle(style1) { append("printf(\"") }
                    withStyle(style2) { append("Review,\nRetain, Repeat") }
                    withStyle(style1) { append("\");") }
                },
                softWrap = false,
                overflow = TextOverflow.Visible,
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 5.dp)
            )
        }
    }
}

@Preview
@Preview(locale = "ko")
@Composable
private fun BannerComponentPrev() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ) {
        BannerComponent(
            
            modifier = Modifier
        )
    }
}