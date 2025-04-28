package com.e_commerce.core.ui.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.e_commerce.core.theme.Theme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = Theme.shapes.default,
        colors = ButtonDefaults.buttonColors(
            containerColor = Theme.colors.backgroundAccent,
            contentColor = Theme.colors.contentOnColorInverse
        )
    ) {
        Text(
            text = text,
            style = Theme.typography.body.one.regular
        )
    }
}