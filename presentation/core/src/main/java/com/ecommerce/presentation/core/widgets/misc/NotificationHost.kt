package com.ecommerce.presentation.core.widgets.misc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.ecommerce.presentation.core.model.NotificationMessage
import com.ecommerce.presentation.core.theme.Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Composable
@Suppress("ModifierMissing")
fun NotificationHost(durationMs: Long = 3_000L) {
    val controller = LocalNotificationController.current
    var currentMessage by remember { mutableStateOf<NotificationMessage?>(null) }
    var isVisible by remember { mutableStateOf<Boolean>(false) }

    LaunchedEffect(controller.events) {
        controller.events.collect { msg ->
            isVisible = true
            currentMessage = msg
            delay(durationMs)
            isVisible = false
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { fullHeight -> -fullHeight }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { fullHeight -> -fullHeight }),
        modifier = Modifier
            .statusBarsPadding()
            .padding(
                top = Theme.dimens.doublePad,
                start = Theme.dimens.doublePad,
                end = Theme.dimens.doublePad
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = Theme.dimens.sixPad),
            colors = CardDefaults
                .cardColors()
                .copy(containerColor = colorResource(currentMessage?.bgColor ?: android.R.color.transparent)),
            shape = Theme.shapes.default,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Theme.dimens.doublePad),
                text = currentMessage?.text ?: "",
                color = colorResource(currentMessage?.textColor ?: android.R.color.transparent),
                textAlign = TextAlign.Center
            )
        }
    }
}

class NotificationController {
    private val _events = MutableSharedFlow<NotificationMessage>(extraBufferCapacity = 1)
    val events: SharedFlow<NotificationMessage> = _events.asSharedFlow()

    fun show(message: NotificationMessage) {
        _events.tryEmit(message)
    }
}

val LocalNotificationController = compositionLocalOf<NotificationController> { NotificationController() }
