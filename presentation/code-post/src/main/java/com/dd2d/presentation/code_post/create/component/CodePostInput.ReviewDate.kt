package com.dd2d.presentation.code_post.create.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.util.format
import com.dd2d.core.presentation.main_text.Main700Text
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Year
import java.time.ZoneId
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReviewDateInput(
    reviewDate: LocalDateTime?,
    onReviewDateChange: (value: LocalDateTime?) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectableDate = rememberSaveable {
        object: SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).epochSecond <= utcTimeMillis
            }

            override fun isSelectableYear(year: Int): Boolean {
                return Year.now().value <= year
            }
        }
    }

    var setReviewDate by remember { mutableStateOf(false) }
    var openDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        selectableDates = selectableDate
    )

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            Main700Text(
                text = "리뷰 설정",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                lineHeight = 24.sp,
            )
            Checkbox(
                checked = setReviewDate,
                onCheckedChange = {
                    setReviewDate = it
                    onReviewDateChange(if(it) LocalDateTime.now() else null)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
        AnimatedVisibility(visible = setReviewDate) {
            val reviewDateText = reviewDate?.format("yyyy년 M월 d일")?: "-년 -월 -일"
            val shape = MaterialTheme.shapes.small
            Main700Text(
                text = reviewDateText,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = shape)
                    .clickable {
                        openDatePicker = true
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        if(openDatePicker) {
            Box(modifier = Modifier.fillMaxWidth(0.8F)) {
                DatePickerDialog(
                    onDismissRequest = { openDatePicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDatePicker = false
                                datePickerState.selectedDateMillis?.toLocalDateTime().let(onReviewDateChange)
                            }
                        ) {
                            Text(text = "확인")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { openDatePicker = false }) {
                            Text(text = "취소")
                        }
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = DatePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                    modifier = Modifier.scale(0.8F)
                ) {
                    DatePicker(
                        state = datePickerState,
                        headline = null,
                        showModeToggle = false,
                        title = null,
                        colors = DatePickerDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.background,
                        ),
                    )
                }
            }
        }
    }
}

private fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault()) // 시스템 기본 시간대 적용
        .toLocalDateTime()
}
