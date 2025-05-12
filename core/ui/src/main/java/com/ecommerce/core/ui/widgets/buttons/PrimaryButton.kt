package com.ecommerce.core.ui.widgets.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ecommerce.core.ui.theme.Theme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        modifier = Modifier
            .height(52.dp)
            .then(modifier),
        enabled = enabled,
        onClick = onClick,
        shape = Theme.shapes.default,
        colors = ButtonDefaults.buttonColors(
            containerColor = Theme.colors.backgroundAccent,
            contentColor = Theme.colors.contentOnColorInverse,
            disabledContainerColor = Theme.colors.backgroundAccentDisabled,
            disabledContentColor = Theme.colors.contentOnColorInverseDisabled
        )
    ) {
        Text(
            text = text,
            style = Theme.typography.body.one.regular,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview_Enabled_Dark() {
    Theme(darkTheme = true) {
        PrimaryButton(
            text = "Continue",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview_Enabled_Light() {
    Theme(darkTheme = false) {
        PrimaryButton(
            text = "Continue",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        )
    }
}

@Preview
@Composable
private fun PrimaryButton_Preview_Disabled_Dark() {
    Theme(darkTheme = true) {
        PrimaryButton(
            text = "Continue",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
    }
}

@Preview
@Composable
private fun PrimaryButton_Preview_Disabled_Light() {
    Theme(darkTheme = false) {
        PrimaryButton(
            text = "Continue",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
    }
}
