package com.dd2d.ori_android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.dd2d.core.presentation.theme.AppTheme
import com.dd2d.core.presentation.theme.LocalHansType
import com.dd2d.core.presentation.theme.hansType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val appViewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()

        setContent {
            AppTheme {
                CompositionLocalProvider(
                    LocalDensity provides Density(LocalDensity.current.density, 1F),
                    LocalHansType provides hansType
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        appViewModel.startDestination?.let { destination ->
                            AppNavHost(
                                startDestination = destination,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
