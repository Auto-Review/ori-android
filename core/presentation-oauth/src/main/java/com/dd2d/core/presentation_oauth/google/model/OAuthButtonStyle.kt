package com.dd2d.core.presentation_oauth.google.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import com.dd2d.core.presentation_oauth.R

class OAuthButtonStyle(
    val text: String,
    val textStyle: TextStyle,
    val icon: ImageVector,
    val shape: Shape,
    val backgroundColor: Color,
)

object OAuthButtonDefault {
    @Composable
    fun googleOAuthButtonStyle(
        text: String = "sign in with google",
        textStyle: TextStyle = TextStyle.Default,
        icon: ImageVector = ImageVector.vectorResource(R.drawable.google),
        shape: Shape = RectangleShape,
        backgroundColor: Color = Color.White,
    ): OAuthButtonStyle {
        return OAuthButtonStyle(
            text = text,
            textStyle = textStyle,
            icon = icon,
            shape = shape,
            backgroundColor = backgroundColor,
        )
    }
}