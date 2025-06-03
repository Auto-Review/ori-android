package com.dd2d.core.notifier

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.app.NotificationCompat

interface NotifyScope {
	/** 알림 제목 */
	var title: String

	/** 알림 내용 본문 */
	var content: String

	/** 알림 아이콘 리소스 ID */
	var iconRes: Int

	/** 알림 클릭 시 실행될 [PendingIntent] */
	var pendingIntent: PendingIntent?

	/** [PendingIntent] 생성 함수 */
	fun createPendingIntent(
		context: Context,
		targetActivity: Class<out Activity>,
		requestCode: Int,
		intentBuilder: Intent.() -> Unit,
	): PendingIntent

	/** 알림에 추가할 액션 목록 */
	val actions: MutableList<NotificationCompat.Action>

	/** 알림 클릭 시 자동으로 제거될지 여부 */
	var autoCancel: Boolean

	/** 알림 그룹을 지정할 때 사용하는 키 */
	var groupKey: String

	/** 이 알림이 그룹 요약(Group Summary) 역할인지 여부 */
	var groupSummary: Boolean

	/** 알림에 액션을 추가하는 함수 */
	fun addAction(action: NotificationCompat.Action) { actions.add(action) }

	/** 알림 우선순위 (NotificationCompat.PRIORITY_*) */
	var priority: Int

	/** 알림 스타일 (예: BigTextStyle, InboxStyle 등) */
	var style: NotificationCompat.Style?

	/** 진동 패턴 (null이면 진동 없음) */
	var vibrationPattern: List<Long>?

	/** 알림 사운드 Uri (null이면 무음) */
	var soundUri: Uri?

	/** 큰 아이콘 (예: 프로필 이미지 등) */
	var largeIcon: Bitmap?

	/** 알림 색상 (배경/아이콘 강조색 등) */
	var color: Int

	/** 알림을 무음으로 설정할지 여부 */
	var isSilent: Boolean

	/** 프로그레스 바 최대값 */
	var progressMax: Int

	/** 프로그레스 바 현재값 */
	var progressCurrent: Int

	/** 프로그레스 바가 indeterminate 상태인지 여부 */
	var progressIndeterminate: Boolean

	/** 알림에 표시될 부가 텍스트 */
	var subText: String?

	/** 상태바에 표시될 알림 티커 텍스트 (일부 기기에서만 표시됨) */
	var ticker: String?

	/** 동일한 알림에 대해 경고음을 한 번만 낼지 여부 */
	var onlyAlertOnce: Boolean

	/** 알림이 ongoing 상태인지 여부 (사용자가 제거 불가능) */
	var ongoing: Boolean
}