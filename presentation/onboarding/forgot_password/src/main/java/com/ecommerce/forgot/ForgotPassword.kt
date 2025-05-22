package com.ecommerce.forgot

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ecommerce.presentation.core.extensions.conditional
import com.ecommerce.presentation.core.extensions.observeAsText
import com.ecommerce.presentation.core.extensions.rememberKeyboardVisibility
import com.ecommerce.presentation.core.theme.Theme
import com.ecommerce.presentation.core.widgets.buttons.BackButton
import com.ecommerce.presentation.core.widgets.buttons.PrimaryButton
import com.ecommerce.presentation.core.widgets.inputs.EmailInput

private const val IMAGE_CONTAINER_RATIO = 0.5f
private const val BUTTONS_CONTAINER_RATIO = 0.6f

@Composable
fun ForgotPasswordScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val shapeSize = Theme.dimens.triplePad
    val state by viewModel.state.collectAsStateWithLifecycle()
    val emailTextState = remember { TextFieldState(viewModel.state.value.email) }

    LaunchedEffect(emailTextState) {
        snapshotFlow { emailTextState.text }
            .observeAsText()
            .collect(viewModel::onEmailUpdate)
    }

    LaunchedEffect(onBackClick) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ForgotPasswordEffect.EmailSent -> onBackClick()
            }
        }
    }

    ForgotPassword(
        modifier = modifier,
        emailTextState = emailTextState,
        state = state,
        onRestoreClick = viewModel::onRestorePasswordClick,
        onBackClick = onBackClick,
        shapeSize = shapeSize
    )
}

@Composable
private fun ForgotPassword(
    emailTextState: TextFieldState,
    state: ForgotPasswordState,
    onRestoreClick: () -> Unit,
    onBackClick: () -> Unit,
    shapeSize: Dp,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        val isKeyboardVisible by rememberKeyboardVisibility()
        val buttonsHeightRatio = if (isKeyboardVisible) 1f else BUTTONS_CONTAINER_RATIO
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(IMAGE_CONTAINER_RATIO)
                .align(Alignment.TopCenter),
            painter = painterResource(R.drawable.sign_up_top_image),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.TopStart),
            visible = !isKeyboardVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            BackButton(onClick = onBackClick)
        }

        ButtonsContainer(
            isKeyboardVisible = isKeyboardVisible,
            emailTextState = emailTextState,
            emailError = state.emailError,
            isButtonEnabled = state.isButtonEnabled,
            isLoading = state.isLoading,
            modifier = Modifier
                .animateContentSize()
                .fillMaxHeight(buttonsHeightRatio),
            onRestoreClick = onRestoreClick,
            shapeSize = shapeSize,
        )
    }
}

@Composable
private fun BoxScope.ButtonsContainer(
    isKeyboardVisible: Boolean,
    emailTextState: TextFieldState,
    emailError: String?,
    isButtonEnabled: Boolean,
    isLoading: Boolean,
    shapeSize: Dp,
    modifier: Modifier = Modifier,
    onRestoreClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .conditional(
                condition = !isKeyboardVisible,
                ifTrue = { Modifier.clip(containerShape(with(LocalDensity.current) { shapeSize.toPx() })) }
            )
            .verticalScroll(rememberScrollState())
            .background(color = Theme.colors.backgroundPrimary)
            .padding(horizontal = Theme.dimens.doublePad),
        verticalArrangement = Arrangement.Top
    ) {
        ForgotPasswordInputs(
            isKeyboardVisible,
            shapeSize,
            emailTextState,
            emailError,
            onRestoreClick,
            isButtonEnabled,
        )
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
    }
    AnimatedVisibility(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .imePadding()
            .padding(horizontal = Theme.dimens.doublePad)
            .padding(bottom = Theme.dimens.doublePad),
        visible = isKeyboardVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        PrimaryButton(
            enabled = isButtonEnabled && !isLoading,
            modifier = Modifier.fillMaxWidth(),
            onClick = onRestoreClick,
            text = stringResource(R.string.restore_password)
        )
    }
}

@Composable
private fun ColumnScope.ForgotPasswordInputs(
    isKeyboardVisible: Boolean,
    shapeSize: Dp,
    emailTextState: TextFieldState,
    emailError: String?,
    onRestoreClick: () -> Unit,
    isButtonEnabled: Boolean,
) {
    val spacerHeight = when (isKeyboardVisible) {
        true -> with(LocalDensity.current) {
            WindowInsets.systemBars.getTop(LocalDensity.current).toDp()
        } + Theme.dimens.doublePad

        false -> shapeSize + Theme.dimens.doublePad
    }
    Spacer(modifier = Modifier.height(spacerHeight))
    Text(
        text = stringResource(R.string.forgot_password),
        style = Theme.typography.heading.one.semiBold,
        color = Theme.colors.contentPrimary
    )
    Spacer(modifier = Modifier.height(Theme.dimens.doublePad))
    EmailInput(
        state = emailTextState,
        errorText = emailError,
        placeholder = stringResource(R.string.your_email)
    )
    AnimatedVisibility(
        modifier = Modifier.fillMaxHeight(),
        visible = !isKeyboardVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Spacer(modifier = Modifier.height(Theme.dimens.singlePad))
        PrimaryButton(
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
            onClick = onRestoreClick,
            text = stringResource(R.string.restore_password)
        )
    }
}

private fun containerShape(curveHeightPx: Float) = GenericShape { size, _ ->
    val w = size.width
    val h = size.height
    moveTo(0f, curveHeightPx)
    quadraticTo(x1 = 3 * w / 4f, y1 = 2 * curveHeightPx, x2 = w, y2 = curveHeightPx)
    lineTo(w, h)
    lineTo(0f, h)
    close()
}

@Preview(showBackground = true)
@Composable
private fun ForgotPasswordScreenPreview() {
    Theme {
        ForgotPassword(
            modifier = Modifier,
            emailTextState = TextFieldState("alexander@gmail.com"),
            state = ForgotPasswordState.default().copy(
                emailError = "Email error",
            ),
            onRestoreClick = {},
            onBackClick = {},
            shapeSize = Theme.dimens.triplePad
        )
    }
}
