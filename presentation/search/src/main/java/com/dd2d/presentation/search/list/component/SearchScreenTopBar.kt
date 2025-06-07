package com.dd2d.presentation.search.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.icon.BackIcon
import com.dd2d.core.presentation.icon.CloseIcon
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.MainColor
import com.dd2d.core.presentation.theme.tp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreenTopBar(
  searchState: TextFieldState,
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val textStyle = TextStyle.Default.copy(
    fontWeight = FontWeight.W500,
    color = Color.Black,
    fontSize = 12.tp,
    lineHeight = 24.tp,
  )

  TopAppBar(
    navigationIcon = {
      IconButton(onClick = onBack) {
        BackIcon()
      }
    },
    title = {
      BasicTextField(
        state = searchState,
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = textStyle,
        cursorBrush = SolidColor(MainColor),
        decorator = { innerTextField ->
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
              .fillMaxWidth()
              .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(6.dp))
              .padding(horizontal = 12.dp, vertical = 7.dp)
          ) {
            Box(modifier = Modifier.weight(1f)) {
              if(searchState.text.isEmpty()) {
                Text(
                  text = "Hello, Ori :)",
                  style = textStyle,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
              innerTextField()
            }
            if(searchState.text.isNotEmpty()) {
              CloseIcon(
                modifier = Modifier
                  .clip(CircleShape)
                  .clickable(onClick = searchState::clearText)
              )
            }
          }
        },
        modifier = Modifier.fillMaxWidth()
      )
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = Color.White
    ),
    modifier = modifier.fillMaxWidth()
  )
}

@Preview
@Composable
private fun SearchScreenTopBarPrev() {
  AppTheme {
    SearchScreenTopBar(
      searchState = remember { TextFieldState() },
      onBack = {},
      modifier = Modifier.background(Color.White)
    )
  }
}