package com.dd2d.core.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController

/** 바텀 내비게이션 바에 사용할 아이템 인터페이스
 *
 * 구현 예시 1 - `route`에 파라미터가 없을 때
 *
 * ```
 * @Serializable
 * data object MyPageScreenRoute: ScreenRoute, BottomBarItem {
 * 	override val route: ScreenRoute = MyPageScreenRoute
 * 	override val labelRes: Int? = null
 * 	override val selectedLabelRes: Int? = null
 * 	override val iconRes: Int? = null
 * 	override val selectedIconRes: Int? = null
 *
 * 	override fun navigate(navController: NavController) {
 * 		navController.toMyPageScreen()
 * 	}
 * }
 *
 * fun NavController.toMyPageScreen() {
 * 	navigateWithClearBackStack(route = MyPageScreenRoute, launchSingleTop = true)
 * }
 *
 * ```
 *
 * 구현 예시 2 - `route`에 파라미터가 있을 때
 *
 * ```
 * @Serializable
 * data class MyPageScreenRoute(val param: String): ScreenRoute, BottomBarItem {
 * 	 override val route: ScreenRoute = this
 * 	 override val labelRes: Int? = null
 * 	 override val selectedLabelRes: Int? = null
 * 	 override val iconRes: Int? = null
 * 	 override val selectedIconRes: Int? = null
 *
 * 	 override fun navigate(navController: NavController) {
 * 	   navController.navigateWithClearBackStack(param = this.param)
 * 	 }
 * }
 *
 * fun NavController.toMyPageScreen(param: String) {
 * 	 navigateWithClearBackStack(
 * 	   route = MyPageScreenRoute(param = param),
 * 	   launchSingleTop = true
 *   )
 * }
 * ```
 *
 * @see ScreenRoute
 *
 * @property route 아이템의 route. [ScreenRoute]
 * @property labelRes 바텀 바에 보여질 텍스트. [StringRes]
 * @property selectedLabelRes 바텀 바에 보여질 선택된 텍스트. [StringRes]
 * @property iconRes 바텀 바에 보여질 아이콘. [DrawableRes]
 * @property selectedIconRes 바텀 바에 보여질 선택된아이콘. [DrawableRes]
 * @property enabled 아이템의 활성화 여부. 기본적으로 `true`
 */
interface BottomBarItem {
  val route: ScreenRoute

  @get:StringRes
  val labelRes: Int?

  @get:StringRes
  val selectedLabelRes: Int?
    get() = labelRes

  @get:DrawableRes
  val iconRes: Int?

  @get:DrawableRes
  val selectedIconRes: Int?
    get() = iconRes

  val enabled: Boolean
    get() = true

  /** 아이템 클릭 시 이동하기 위해 호출되는 함수. */
  fun navigate(navController: NavController)
}