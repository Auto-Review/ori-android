package com.dd2d.presentation.my.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.core.state.DataState
import com.dd2d.core.presentation.icon.VectorIconButton
import com.dd2d.core.presentation.main_text.Main500Text
import com.dd2d.core.presentation.main_text.Main700Text
import com.dd2d.domain.user.model.User

@Composable
internal fun UserComponent(
    userState: DataState<User>,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 20.dp)
            ){
                when(userState) {
                    is DataState.Loading -> {

                    }
                    is DataState.Error -> {

                    }
                    is DataState.Success -> {
                        UserDataField(label = "Name", value = userState.data.nickname, modifier = Modifier.fillMaxWidth())
                        UserDataField(label = "Email", value = userState.data.email, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
            VectorIconButton(
                icon = Icons.Default.Edit,
                modifier = Modifier.align(Alignment.TopEnd).scale(0.8F)
            ) {

            }
        }
    }
}

@Composable
private fun UserDataField(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Main700Text(
            text = label,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(0.2F)
        )
        Main500Text(
            text = value,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}