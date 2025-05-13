@file:OptIn(ExperimentalMaterial3Api::class)

package com.ecommerce.presentation.core.widgets.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ecommerce.presentation.core.theme.Theme
import com.ecommerce.presentation.core.theme.rippleConfiguration

private fun Modifier.default() = this
    .height(56.dp)
    .fillMaxWidth()

@Composable
private fun InputPlaceholder(text: String) {
    Text(
        color = Theme.colors.contentSecondary,
        text = text,
        style = Theme.typography.body.one.regular
    )
}

@Composable
private fun FieldDecoration(
    errorText: String?,
    content: @Composable () -> Unit
) {
    Column(horizontalAlignment = Alignment.End) {
        content()
        // always ensure some space for error text, to prevent "jumping" in runtime
        Box(
            modifier = Modifier.height(Theme.dimens.triplePad),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(end = Theme.dimens.doublePad),
                color = Theme.colors.contentSale,
                text = errorText ?: "",
                style = Theme.typography.body.one.regular
            )
        }
    }
}

@Composable
fun EmailInput(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorText: String? = null
) {
    BasicTextField(
        state = state,
        modifier = modifier,
        textStyle = Theme.typography.body.one.regular.copy(color = Theme.colors.contentPrimary),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerTextField ->
            FieldDecoration(errorText = errorText) {
                Box(
                    modifier = Modifier
                        .default()
                        .clip(Theme.shapes.default)
                        .background(color = Theme.colors.backgroundSecondary)
                        .padding(horizontal = Theme.dimens.doublePad),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (state.text.isEmpty()) InputPlaceholder(placeholder)
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun PasswordInput(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorText: String? = null
) {
    var showPassword by remember { mutableStateOf(false) }
    BasicSecureTextField(
        state = state,
        textObfuscationMode = if (showPassword)
            TextObfuscationMode.Visible
        else
            TextObfuscationMode.RevealLastTyped,
        modifier = modifier,
        textStyle = Theme.typography.body.one.regular.copy(color = Theme.colors.contentPrimary),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        decorator = { innerTextField ->
            FieldDecoration(errorText = errorText) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .default()
                            .align(Alignment.CenterStart)
                            .clip(Theme.shapes.default)
                            .background(color = Theme.colors.backgroundSecondary)
                            .padding(
                                start = Theme.dimens.doublePad,
                                end = Theme.dimens.sixPad
                            ),
                        contentAlignment = Alignment.CenterStart
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
                                .requiredSize(Theme.dimens.sixPad)
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
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailInput_Preview_Normal() {
    Theme {
        val state = remember { TextFieldState() }
        EmailInput(
            state = state,
            placeholder = "email@example.com",
            modifier = Modifier.padding(16.dp),
            errorText = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmailInput_Preview_Error() {
    Theme {
        val state = remember { TextFieldState("bad-email") }
        EmailInput(
            state = state,
            placeholder = "email@example.com",
            modifier = Modifier.padding(16.dp),
            errorText = "Invalid email"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordInput_Preview_Normal() {
    Theme {
        val state = remember { TextFieldState() }
        PasswordInput(
            state = state,
            placeholder = "Password",
            modifier = Modifier.padding(16.dp),
            errorText = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordInput_Preview_Error() {
    Theme {
        val state = remember { TextFieldState("123") }
        PasswordInput(
            state = state,
            placeholder = "Password",
            modifier = Modifier.padding(16.dp),
            errorText = "Too short"
        )
    }
}
