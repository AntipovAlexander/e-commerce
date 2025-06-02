package com.ecommerce.signup

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
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
import com.ecommerce.presentation.core.widgets.inputs.PasswordInput
import com.ecommerce.presentation.core.widgets.misc.FullScreenLoader
import com.ecommerce.presentation.core.widgets.misc.LocalNotificationController

private const val IMAGE_CONTAINER_RATIO = 0.5f
private const val BUTTONS_CONTAINER_RATIO = 0.6f

@Composable
fun SignUpScreen(
    onExitRequest: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val notificationController = LocalNotificationController.current
    val emailTextState = remember { TextFieldState(viewModel.state.value.email) }
    val enterPasswordState = remember { TextFieldState(viewModel.state.value.password) }
    val repeatPasswordState = remember { TextFieldState(viewModel.state.value.repeatedPassword) }

    SignUpValidationEffects(
        emailTextState = emailTextState,
        enterPasswordState = enterPasswordState,
        repeatPasswordState = repeatPasswordState,
        onEmailUpdate = viewModel::onEmailUpdate,
        onPasswordUpdate = viewModel::onPasswordUpdate,
        onRepeatedPasswordUpdate = viewModel::onRepeatedPasswordUpdate,
    )
    LaunchedEffect(onExitRequest) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SignUpEffect.SignUpSuccess -> onExitRequest()
                is SignUpEffect.ShowMessage -> notificationController.show(effect.message)
            }
        }
    }
    SignUp(
        emailTextState = emailTextState,
        state = state,
        enterPasswordState = enterPasswordState,
        repeatPasswordState = repeatPasswordState,
        onSignUpClick = viewModel::onSignUpClick,
        onExitRequest = onExitRequest,
        modifier = modifier,
    )
    FullScreenLoader(isLoading = state.isLoading)
}

@Composable
private fun SignUp(
    emailTextState: TextFieldState,
    state: SignUpState,
    enterPasswordState: TextFieldState,
    repeatPasswordState: TextFieldState,
    onSignUpClick: () -> Unit,
    onExitRequest: () -> Unit,
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
            BackButton(onClick = onExitRequest)
        }

        ButtonsContainer(
            isKeyboardVisible = isKeyboardVisible,
            shapeSize = Theme.dimens.triplePad,
            emailTextState = emailTextState,
            emailError = state.emailError,
            enterPasswordState = enterPasswordState,
            passwordError = state.passwordError,
            repeatPasswordState = repeatPasswordState,
            repeatPasswordError = state.repeatedPasswordError,
            isButtonEnabled = state.isButtonEnabled && !state.isLoading,
            onSignUpClick = onSignUpClick,
            modifier = Modifier
                .animateContentSize()
                .fillMaxHeight(buttonsHeightRatio),
        )
    }
}

@Composable
private fun BoxScope.ButtonsContainer(
    isKeyboardVisible: Boolean,
    shapeSize: Dp,
    emailTextState: TextFieldState,
    emailError: String?,
    enterPasswordState: TextFieldState,
    passwordError: String?,
    repeatPasswordState: TextFieldState,
    repeatPasswordError: String?,
    isButtonEnabled: Boolean,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
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
            .padding(horizontal = Theme.dimens.doublePad)
            .imePadding(),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(shapeSize + Theme.dimens.doublePad))
        Text(
            text = stringResource(R.string.lets_get_started),
            style = Theme.typography.heading.one.semiBold,
            color = Theme.colors.contentPrimary
        )
        Spacer(modifier = Modifier.height(Theme.dimens.doublePad))
        EmailInput(
            state = emailTextState,
            errorText = emailError,
            placeholder = stringResource(R.string.your_email)
        )
        PasswordInput(
            state = enterPasswordState,
            errorText = passwordError,
            placeholder = stringResource(R.string.set_your_password)
        )
        PasswordInput(
            state = repeatPasswordState,
            errorText = repeatPasswordError,
            placeholder = stringResource(R.string.repeat_your_password)
        )
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = isButtonEnabled,
            text = stringResource(R.string.sign_up),
            onClick = onSignUpClick
        )
        Spacer(modifier = Modifier.height(Theme.dimens.doublePad))
    }
}

@Composable
private fun SignUpValidationEffects(
    emailTextState: TextFieldState,
    enterPasswordState: TextFieldState,
    repeatPasswordState: TextFieldState,
    onEmailUpdate: (String) -> Unit,
    onPasswordUpdate: (String) -> Unit,
    onRepeatedPasswordUpdate: (String) -> Unit,
) {
    LaunchedEffect(emailTextState) {
        snapshotFlow { emailTextState.text }
            .observeAsText()
            .collect(onEmailUpdate)
    }

    LaunchedEffect(enterPasswordState) {
        snapshotFlow { enterPasswordState.text }
            .observeAsText()
            .collect(onPasswordUpdate)
    }

    LaunchedEffect(repeatPasswordState) {
        snapshotFlow { repeatPasswordState.text }
            .observeAsText()
            .collect(onRepeatedPasswordUpdate)
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
private fun SignUpScreenPreview() {
    Theme {
        SignUpScreen(onExitRequest = {})
    }
}
