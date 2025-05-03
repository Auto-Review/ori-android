package com.dd2d.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.R


val HansFontFamily = FontFamily(
    Font(R.font.black_han_sans),
)

class HansTypography internal constructor (
    val topBarTitle: TextStyle,
    val tabTitle: TextStyle,
    val label: TextStyle,
    val banner: TextStyle,
)

val LocalHansType = staticCompositionLocalOf<HansTypography> {
    error("No HansTypography provided")
}

val hansType: HansTypography
    @Composable get() = HansTypography(
        topBarTitle = TextStyle(
            fontFamily = HansFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 56.sp,
        ),
        tabTitle = TextStyle(
            fontFamily = HansFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 56.sp,
        ),
        label = TextStyle(
            fontFamily = HansFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 56.sp,
        ),
        banner = TextStyle(
            fontFamily = HansFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 20.sp,
            lineHeight = 24.sp,
        ),
    )
