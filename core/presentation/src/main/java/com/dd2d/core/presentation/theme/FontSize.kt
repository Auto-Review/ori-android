package com.dd2d.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

val Int.tp: TextUnit
  @Composable get() = with(LocalDensity.current) { this@tp.dp.toSp() }

val Float.tp: TextUnit
  @Composable get() = with(LocalDensity.current) { this@tp.dp.toSp() }

val Double.tp: TextUnit
  @Composable get() = with(LocalDensity.current) { this@tp.dp.toSp() }