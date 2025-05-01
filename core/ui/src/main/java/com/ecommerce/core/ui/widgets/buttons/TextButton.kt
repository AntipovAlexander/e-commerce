package com.ecommerce.core.ui.widgets.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ecommerce.core.ui.theme.Theme
import com.ecommerce.core.ui.theme.ripple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    CompositionLocalProvider(
        LocalRippleConfiguration provides ripple()
    ) {
        TextButton(
            modifier = Modifier
                .height(52.dp)
                .then(modifier),
            shape = Theme.shapes.default,
            enabled = enabled,
            onClick = onClick
        ) {
            Text(
                text = text,
                color = Theme.colors.contentPrimary,
                style = Theme.typography.body.one.underline
            )
        }
    }
}
