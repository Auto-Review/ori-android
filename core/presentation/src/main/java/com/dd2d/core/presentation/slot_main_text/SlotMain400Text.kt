package com.dd2d.core.presentation.slot_main_text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.MainText

@Composable
fun SlotMain400Text(
  text: String,
  modifier: Modifier = Modifier,
  slotVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
  slotHorizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
  fillTextWidth: Boolean = false,
  prefix: @Composable (() -> Unit)? = null,
  prefixEndPadding: Dp = 0.dp,
  suffix: @Composable (() -> Unit)? = null,
  suffixStartPadding: Dp = 0.dp,
  color: Color = Color.Black,
  fontSize: TextUnit = 14.sp,
  overFlow: TextOverflow = TextOverflow.Ellipsis,
  textAlign: TextAlign = TextAlign.Start,
  softWrap: Boolean = true,
  maxLine: Int = 1,
  minLine: Int = 1,
  lineHeight: TextUnit = TextUnit.Unspecified,
  letterSpacing: TextUnit = TextUnit.Unspecified,
  fontFamily: FontFamily? = null,
  textStyle: TextStyle = LocalTextStyle.current,
) {
  Row(
    verticalAlignment = slotVerticalAlignment,
    horizontalArrangement = slotHorizontalArrangement,
    modifier = modifier
  ) {
    prefix?.invoke()
    Spacer(Modifier.width(prefixEndPadding))
    MainText(
      text = text,
      color = color,
      fontSize = fontSize,
      fontWeight = FontWeight.W400,
      overFlow = overFlow,
      textAlign = textAlign,
      softWrap = softWrap,
      maxLine = maxLine,
      minLine = minLine,
      lineHeight = lineHeight,
      letterSpacing = letterSpacing,
      textStyle = textStyle,
      fontFamily = fontFamily,
      modifier = Modifier.weight(1F, fillTextWidth)
    )
    Spacer(Modifier.width(suffixStartPadding))
    suffix?.invoke()
  }
}