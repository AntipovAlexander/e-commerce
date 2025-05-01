package com.e_commerce.core.ui.widgets.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.e_commerce.core.ui.theme.Theme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
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