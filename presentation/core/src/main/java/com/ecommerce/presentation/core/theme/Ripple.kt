package com.ecommerce.presentation.core.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun rippleConfiguration() = RippleConfiguration(
    color = Theme.colors.backgroundAccent,
    rippleAlpha = RippleAlpha(
        pressedAlpha = 0.1f,
        draggedAlpha = 0.1f,
        focusedAlpha = 0.1f,
        hoveredAlpha = 0.1f
    )
)
