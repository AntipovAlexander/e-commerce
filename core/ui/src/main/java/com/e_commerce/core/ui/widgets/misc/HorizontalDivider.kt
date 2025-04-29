package com.e_commerce.core.ui.widgets.misc

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.e_commerce.core.ui.theme.Theme

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = Theme.dimens.eighthPad,
    color: Color = Theme.colors.backgroundSecondary
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}