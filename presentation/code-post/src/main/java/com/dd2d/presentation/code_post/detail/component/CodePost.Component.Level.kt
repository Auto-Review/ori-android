package com.dd2d.presentation.code_post.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.image.PainterImage
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.presentation.code_post.R

@Composable
internal fun CodePostLevelComponent(
  level: Int,
  onReviewCreateClick: (() -> Unit)?,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      repeat(5) { index ->
        PainterImage(
          res = if (level >= index + 1) R.drawable.star_fill else R.drawable.star,
          modifier = Modifier.size(16.dp)
        )
      }
    }

    onReviewCreateClick?.let {
      Main700Text(
        text = "::Review",
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
        modifier = Modifier
          .clickable(onClick = onReviewCreateClick)
          .padding(horizontal = 12.dp, vertical = 8.dp)
      )
    }

  }
}