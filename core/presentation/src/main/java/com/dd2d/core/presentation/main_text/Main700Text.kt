package com.dd2d.core.presentation.main_text

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun Main700Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = 14.sp,
    textStyle: TextStyle = LocalTextStyle.current,
    fontFamily: FontFamily? = null,
    overFlow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    softWrap: Boolean = true,
    maxLine: Int = 1,
    minLine: Int = 1,
    lineHeight: TextUnit = TextUnit. Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
) {
    MainText(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        color = color,
        fontWeight = FontWeight.Bold,
        overFlow = overFlow,
        textAlign = textAlign,
        softWrap = softWrap,
        maxLine = maxLine,
        minLine = minLine,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textStyle = textStyle,
        fontFamily = fontFamily
    )
}