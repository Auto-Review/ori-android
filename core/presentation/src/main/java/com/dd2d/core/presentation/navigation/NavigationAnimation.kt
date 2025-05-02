package com.dd2d.core.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val DEFAULT_FADE_ANIMATION_DURATION = 300
private const val DEFAULT_SLIDE_ANIMATION_DURATION = 300
private val DEFAULT_TRANSFORM_ORIGIN = TransformOrigin(0.5F, 0F)

fun defaultFadeIn(): EnterTransition = fadeIn(animationSpec = tween(DEFAULT_FADE_ANIMATION_DURATION))
fun defaultFadeOut(): ExitTransition = fadeOut(animationSpec = tween(DEFAULT_FADE_ANIMATION_DURATION))

fun defaultScaleIn(): EnterTransition = scaleIn(animationSpec = tween(DEFAULT_SLIDE_ANIMATION_DURATION), initialScale = 0.95F)//, transformOrigin = DEFAULT_TRANSFORM_ORIGIN)
fun defaultScaleOut(): ExitTransition = scaleOut(animationSpec = tween(DEFAULT_SLIDE_ANIMATION_DURATION), targetScale = 0.95F)//, transformOrigin = DEFAULT_TRANSFORM_ORIGIN)

fun defaultSlideInHorizontal(): EnterTransition = slideInHorizontally(animationSpec = tween(DEFAULT_SLIDE_ANIMATION_DURATION)) { it }
fun defaultSlideOutHorizontal(): ExitTransition = slideOutHorizontally(animationSpec = tween(DEFAULT_SLIDE_ANIMATION_DURATION)) { it }

fun defaultSlideInVertical(): EnterTransition = slideInVertically(animationSpec = tween(DEFAULT_SLIDE_ANIMATION_DURATION)) { it }
fun defaultSlideOutVertical(): ExitTransition = slideOutVertically(animationSpec = tween(DEFAULT_SLIDE_ANIMATION_DURATION)) { it }

inline fun <reified T: Any> NavGraphBuilder.fadeScreen(
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        enterTransition = { defaultFadeIn() },
        exitTransition = { defaultFadeOut() },
        popEnterTransition = { defaultFadeIn() },
        popExitTransition = { defaultFadeOut() },
        content = content,
    )
}

inline fun <reified T: Any> NavGraphBuilder.scaleScreen(
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        enterTransition = { defaultScaleIn() },
        exitTransition = { defaultScaleOut() },
        popEnterTransition = { defaultScaleIn() },
        popExitTransition = { defaultScaleOut() },
        content = content,
    )
}

inline fun <reified T: Any> NavGraphBuilder.horizontalScreen(
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        enterTransition = { defaultSlideInHorizontal() },
        exitTransition = { defaultFadeOut() },
        popEnterTransition = { defaultFadeIn() },
        popExitTransition = { defaultSlideOutHorizontal() },
        content = content,
    )
}

inline fun <reified T: Any> NavGraphBuilder.verticalScreen(
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        enterTransition = { defaultSlideInVertical() },
        exitTransition = { defaultFadeOut() },
        popEnterTransition = { defaultFadeIn() },
        popExitTransition = { defaultSlideOutVertical() },
        content = content,
    )
}