package com.ecommerce.presentation.core.widgets.misc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ecommerce.presentation.core.theme.Theme
import kotlinx.coroutines.delay

/**
 * Displays a full-screen loading overlay with a circular progress indicator.
 *
 * This composable is intended to be used as a global loader that covers the entire UI,
 * preventing user interaction while a background operation is in progress.
 *
 * The loader remains visible for at least a minimum display duration to avoid flickering for very fast operations.
 *
 * @param isLoading Whether the loader should be shown. When set to true, the loader will appear after [initialDelayMs].
 *                 When set to false, the loader will disappear,
 *                 but only after [minDisplayMs] has elapsed since it became visible.
 * @param modifier Modifier to be applied to the loader container.
 * @param initialDelayMs The delay in milliseconds before showing the loader after [isLoading] becomes true.
 *                      Default is 500ms.
 * @param minDisplayMs The minimum time in milliseconds the loader should remain visible once shown.
 *                      Default is 500ms.
 */
@Composable
fun FullScreenLoader(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    initialDelayMs: Long = 500L,
    minDisplayMs: Long = 500L
) {
    var isLoaderVisible by remember { mutableStateOf(false) }
    var loaderShownAt by remember { mutableLongStateOf(0L) }

    LaunchedEffect(isLoading) {
        if (isLoading) {
            isLoaderVisible = false
            delay(initialDelayMs)
            loaderShownAt = System.currentTimeMillis()
            isLoaderVisible = true
        } else {
            if (isLoaderVisible) {
                val elapsed = System.currentTimeMillis() - loaderShownAt
                if (elapsed < minDisplayMs) delay(minDisplayMs - elapsed)
            }
            isLoaderVisible = false
        }
    }

    AnimatedVisibility(
        visible = isLoaderVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Theme.colors.backgroundPrimary.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Theme.colors.contentPrimary)
        }
    }
}
