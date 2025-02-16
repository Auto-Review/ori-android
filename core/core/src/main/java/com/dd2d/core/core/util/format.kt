package com.dd2d.core.core.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun YearMonth.format(pattern: String): String = this.format(DateTimeFormatter.ofPattern(pattern))

fun LocalDate.format(pattern: String): String = this.format(DateTimeFormatter.ofPattern(pattern))

fun LocalDateTime.format(pattern: String): String = this.format(DateTimeFormatter.ofPattern(pattern))

fun LocalDateTime.setTime(hour: Int, minute: Int, second: Int): LocalDateTime = this.withHour(hour).withMinute(minute).withSecond(second)

/** 현재 날짜에서 `00시 00분 00초 00나노초`로 설정된 [LocalDateTime]을 반환합니다.*/
fun LocalDateTime.setTimeToMin(): LocalDateTime = this.withHour(0).withMinute(0).withSecond(0).withNano(0)

/** 현재 날짜에서 `23시 59분 59초 999,999,999나노초`로 설정된 [LocalDateTime]을 반환합니다.*/
fun LocalDateTime.setTimeToMax(): LocalDateTime = this.withHour(23).withMinute(59).withSecond(59).withNano(999_999_999)

val LocalDateTime.isToday: Boolean get() = this.toLocalDate() == LocalDate.now()
val LocalDateTime.isNotToday: Boolean get() = this.toLocalDate() != LocalDate.now()

fun String.dateStringToLocalDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): LocalDate = LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern))
fun String.dateStringToLocalDateTime(pattern: String = "yyyy-MM-dd HH:mm:ss"): LocalDateTime = LocalDateTime.parse(this, DateTimeFormatter.ofPattern(pattern))

fun LocalDateTime.toUTCString(): String = atOffset(ZoneOffset.UTC).toString()