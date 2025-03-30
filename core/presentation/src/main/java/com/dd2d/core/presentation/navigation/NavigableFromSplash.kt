package com.dd2d.core.presentation.navigation

import androidx.navigation.NavController

/** 스플래시 스크린 다음으로 이동할 수 있는 화면의 인터페이스
 *
 * 구현 예시 1 - `route`에 프로퍼티가 없는 경우
 *
 * ```
 *      @Serializable
 *      data object AuthScreenRoute: ScreenRoute, NavigableFromSplash {
 *          override fun navigate(navController: NavController) {
 *              navController.toAuthScreen()
 * 	        }
 *      }
 *
 *      fun NavController.toAuthScreen() {
 *      	navigateWithClearBackStack(route = AuthScreenRoute), launchSingleTop = true)
 *      }
 * ```
 *
 * 구현 예시 2 - `route`에 프로퍼티가 있는 경우
 *
 * ```
 *      @Serializable
 *      data class AuthScreenRoute(val initialId: String): ScreenRoute, NavigableFromSplash {
 *          override fun navigate(navController: NavController) {
 *              navController.toAuthScreen(initialId = this.initialId)
 * 	        }
 *      }
 *
 *      fun NavController.toAuthScreen(initialId: String = "") {
 *      	navigateWithClearBackStack(route = AuthScreenRoute(initialId), launchSingleTop = true)
 *      }
 * ```
 *
 * @see ScreenRoute
 */
interface NavigableFromSplash {
	/** 다음 화면으로 이동*/
	fun navigate(navController: NavController)
}