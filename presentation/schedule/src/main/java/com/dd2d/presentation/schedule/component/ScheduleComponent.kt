package com.dd2d.presentation.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.util.format
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.hansType
import com.dd2d.domain.schedule.model.ScheduleOnDay
import com.dd2d.domain.schedule.model.ScheduleOnMonth

@Composable
internal fun ScheduleComponent(
    scheduleOnDayList: List<ScheduleOnDay>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "// CHECK LIST",
            style = hansType.label,
        )
        Spacer(Modifier.height(10.dp))
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp, vertical = 10.dp)
            ){
                scheduleOnDayList.forEach { item ->
                    key(item.id) {
                        ScheduleItemComponent(item = item, modifier = Modifier.fillMaxWidth())
                    }
                }
                scheduleOnDayList.ifEmpty {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth().height(100.dp)
                    ) {
                        Text(
                            text = "오늘 할 일이 없을 떄 ?? 뭘 띄워주면 좋을까?",
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScheduleItemComponent(
    item: ScheduleOnDay,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Main700Text(
            text = item.title,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1F)
        )
        Spacer(Modifier.width(10.dp))
        Main700Text(
            text = item.date.format("yyyy-MM-dd"),
            fontSize = 12.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Preview(locale = "ko")
@Composable
private fun ScheduleComponentPrev() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            ScheduleComponent(
                scheduleOnDayList = ScheduleOnMonth.dummy().items,
                modifier = Modifier
            )
        }
    }
}