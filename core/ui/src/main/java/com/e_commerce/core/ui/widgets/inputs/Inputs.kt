package com.e_commerce.core.ui.widgets.inputs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.e_commerce.core.ui.theme.Theme

@Composable
fun PrimaryInput(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    @DrawableRes iconRes: Int? = null,
    onChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onChange,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .then(modifier),
        placeholder = {
            Text(
                color = Theme.colors.contentSecondary,
                text = placeholder,
                style = Theme.typography.body.one.regular
            )
        },
        singleLine = singleLine,
        trailingIcon = icon@{
            iconRes ?: return@icon
            Icon(
                painter = painterResource(iconRes),
                contentDescription = placeholder,
                tint = Theme.colors.contentSecondary,
                modifier = Modifier.size(24.dp)
            )
        },
        shape = Theme.shapes.default,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Theme.colors.backgroundSecondary,
            unfocusedContainerColor = Theme.colors.backgroundSecondary,
            focusedTextColor = Theme.colors.contentPrimary,
            unfocusedTextColor = Theme.colors.contentPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = Theme.typography.body.one.regular,
        keyboardOptions = keyboardOptions
    )
}
