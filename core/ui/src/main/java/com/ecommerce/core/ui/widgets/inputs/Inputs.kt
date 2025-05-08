@file:OptIn(ExperimentalMaterial3Api::class)

package com.ecommerce.core.ui.widgets.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ecommerce.core.ui.theme.Theme
import com.ecommerce.core.ui.theme.rippleConfiguration

private fun Modifier.default() = this
    .height(56.dp)
    .fillMaxWidth()

private val TextFieldDefaultColors
    @Composable
    get() = TextFieldDefaults.colors(
        focusedContainerColor = Theme.colors.backgroundSecondary,
        unfocusedContainerColor = Theme.colors.backgroundSecondary,
        focusedTextColor = Theme.colors.contentPrimary,
        unfocusedTextColor = Theme.colors.contentPrimary,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )

@Composable
private fun InputPlaceholder(text: String) {
    Text(
        color = Theme.colors.contentSecondary,
        text = text,
        style = Theme.typography.body.one.regular
    )
}

@Composable
fun EmailInput(
    text: String,
    placeholder: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = text,
        onValueChange = onChange,
        modifier = Modifier
            .default()
            .then(modifier),
        placeholder = { InputPlaceholder(placeholder) },
        singleLine = true,
        shape = Theme.shapes.default,
        colors = TextFieldDefaultColors,
        textStyle = Theme.typography.body.one.regular,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}

@Composable
fun PasswordInput(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    var showPassword by remember { mutableStateOf(false) }
    BasicSecureTextField(
        state = state,
        textObfuscationMode = if (showPassword)
            TextObfuscationMode.Visible
        else
            TextObfuscationMode.RevealLastTyped,
        modifier = Modifier
            .default()
            .clip(Theme.shapes.default)
            .background(color = Theme.colors.backgroundSecondary)
            .then(modifier),
        textStyle = Theme.typography.body.one.regular,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        decorator = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(
                            start = Theme.dimens.doublePad,
                            end = Theme.dimens.triplePad * 2
                        )
                ) {
                    if (state.text.isEmpty()) InputPlaceholder(placeholder)
                    innerTextField()
                }
                CompositionLocalProvider(
                    LocalRippleConfiguration provides rippleConfiguration()
                ) {
                    Icon(
                        imageVector = if (showPassword)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        tint = Theme.colors.contentSecondary,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .requiredSize(Theme.dimens.triplePad * 2)
                            .padding(Theme.dimens.singleHalfPad)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = { showPassword = !showPassword },
                                indication = ripple(bounded = false)
                            )
                    )
                }
            }
        }
    )
}
