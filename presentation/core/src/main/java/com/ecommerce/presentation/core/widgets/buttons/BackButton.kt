package com.ecommerce.presentation.core.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ecommerce.presentation.core.R
import com.ecommerce.presentation.core.theme.Theme

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .statusBarsPadding()
            .padding(start = Theme.dimens.doublePad)
            .size(Theme.dimens.sixPad)
            .background(
                color = Theme.colors.backgroundPrimary.copy(alpha = 0.5f),
                shape = Theme.shapes.default
            )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            tint = Theme.colors.contentPrimary
        )
    }
}
