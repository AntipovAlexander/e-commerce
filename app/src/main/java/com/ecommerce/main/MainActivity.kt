package com.ecommerce.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ecommerce.presentation.core.theme.Theme
import com.ecommerce.presentation.core.widgets.misc.LocalNotificationController
import com.ecommerce.presentation.core.widgets.misc.NotificationController
import com.ecommerce.presentation.core.widgets.misc.NotificationHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Theme {
                val notificationController = remember { NotificationController() }
                CompositionLocalProvider(
                    LocalNotificationController provides notificationController
                ) {
                    MainNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Theme.colors.backgroundPrimary)
                    )
                    NotificationHost()
                }
            }
        }
    }
}
