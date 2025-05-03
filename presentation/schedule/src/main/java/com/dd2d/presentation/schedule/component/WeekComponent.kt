package com.dd2d.presentation.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.core.presentation.theme.tp
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek

@Composable
internal fun WeekComponent(
    modifier: Modifier = Modifier,
    weeks: List<DayOfWeek> = daysOfWeek(),
    weekColor: Color = Color(0xFFB5BEC6)
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        weeks.forEach { week ->
            Main600Text(
                text = getWeekText(dayOfWeek = week),
                fontSize = 10.tp,
                lineHeight = 12.tp,
                color = weekColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1F)
            )
        }
    }
}

private fun getWeekText(dayOfWeek: DayOfWeek): String {
    return when(dayOfWeek) {
        DayOfWeek.SUNDAY -> "SUN"
        DayOfWeek.MONDAY -> "MON"
        DayOfWeek.TUESDAY -> "TUE"
        DayOfWeek.WEDNESDAY -> "WED"
        DayOfWeek.THURSDAY -> "THU"
        DayOfWeek.FRIDAY -> "FRI"
        DayOfWeek.SATURDAY -> "SAT"
    }
}