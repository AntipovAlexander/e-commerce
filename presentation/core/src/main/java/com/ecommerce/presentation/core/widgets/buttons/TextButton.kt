package com.ecommerce.presentation.core.widgets.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ecommerce.presentation.core.theme.Theme
import com.ecommerce.presentation.core.theme.rippleConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    CompositionLocalProvider(
        LocalRippleConfiguration provides rippleConfiguration()
    ) {
        TextButton(
            modifier = Modifier
                .height(52.dp)
                .then(modifier),
            shape = Theme.shapes.default,
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                contentColor = Theme.colors.contentPrimary,
                disabledContentColor = Theme.colors.contentPrimaryDisabled
            ),
            onClick = onClick
        ) {
            Text(
                text = text,
                style = Theme.typography.body.one.underline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButton_Preview_Enabled_Dark() {
    Theme(darkTheme = false) {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Tap me",
            onClick = {},
            enabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButton_Preview_Enabled_Light() {
    Theme(darkTheme = false) {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Tap me",
            onClick = {},
            enabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButton_Preview_Disabled_Dark() {
    Theme(darkTheme = true) {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Tap me",
            onClick = {},
            enabled = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButton_Preview_Disabled_Light() {
    Theme(darkTheme = false) {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Tap me",
            onClick = {},
            enabled = false
        )
    }
}
