package com.ecommerce.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.input.TextFieldState
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
import com.ecommerce.presentation.core.extensions.observeAsText
import com.ecommerce.presentation.core.theme.Theme
import com.ecommerce.presentation.core.widgets.buttons.PrimaryButton
import com.ecommerce.presentation.core.widgets.buttons.TextButton
import com.ecommerce.presentation.core.widgets.inputs.EmailInput
import com.ecommerce.presentation.core.widgets.inputs.PasswordInput

private const val IMAGE_CONTAINER_RATIO = 0.5f
private const val BUTTONS_CONTAINER_RATIO = 0.6f

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onRestoreClick: () -> Unit,
    onLoggedIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val shapeSize = Theme.dimens.triplePad
    val state by viewModel.state.collectAsStateWithLifecycle()
    val emailTextState = remember { TextFieldState(viewModel.state.value.email) }
    val passwordTextState = remember { TextFieldState(viewModel.state.value.password) }
    LaunchedEffect(emailTextState) {
        snapshotFlow { emailTextState.text }
            .observeAsText()
            .collect(viewModel::onEmailUpdate)
    }
    LaunchedEffect(passwordTextState) {
        snapshotFlow { passwordTextState.text }
            .observeAsText()
            .collect(viewModel::onPasswordUpdate)
    }
    SignIn(
        modifier = modifier,
        emailTextState = emailTextState,
        state = state,
        passwordTextState = passwordTextState,
        onLoggedIn = onLoggedIn,
        onSignUpClick = onSignUpClick,
        onRestoreClick = onRestoreClick,
        shapeSize = shapeSize
    )
}

@Composable
private fun SignIn(
    emailTextState: TextFieldState,
    state: SignInState,
    passwordTextState: TextFieldState,
    onLoggedIn: () -> Unit,
    onSignUpClick: () -> Unit,
    onRestoreClick: () -> Unit,
    shapeSize: Dp,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(IMAGE_CONTAINER_RATIO)
                .align(Alignment.TopCenter),
            painter = painterResource(R.drawable.sign_in_top_image),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        ButtonsContainer(
            emailTextState = emailTextState,
            emailError = state.emailError,
            passwordTextState = passwordTextState,
            passwordError = state.passwordError,
            isButtonEnabled = state.isButtonEnabled,
            onLoggedIn = onLoggedIn,
            onSignUpClick = onSignUpClick,
            onRestoreClick = onRestoreClick,
            shapeSize = shapeSize,
        )
    }
}

@Composable
private fun BoxScope.ButtonsContainer(
    emailTextState: TextFieldState,
    emailError: String?,
    passwordTextState: TextFieldState,
    passwordError: String?,
    isButtonEnabled: Boolean,
    shapeSize: Dp,
    modifier: Modifier = Modifier,
    onLoggedIn: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onRestoreClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(BUTTONS_CONTAINER_RATIO)
            .align(Alignment.BottomCenter)
            .clip(containerShape(with(LocalDensity.current) { shapeSize.toPx() }))
            .background(color = Theme.colors.backgroundPrimary)
            .padding(horizontal = Theme.dimens.doublePad),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(shapeSize + Theme.dimens.doublePad))
        Text(
            text = stringResource(R.string.welcome_back),
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
            state = passwordTextState,
            errorText = passwordError,
            placeholder = stringResource(R.string.your_password)
        )
        Spacer(modifier = Modifier.height(Theme.dimens.singlePad))
        PrimaryButton(
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoggedIn,
            text = stringResource(R.string.log_in)
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.dont_have_an_account),
            onClick = onSignUpClick
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = Theme.typography.body.one.regular,
            color = Theme.colors.contentSecondary,
            text = stringResource(R.string.or)
        )
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.forgot_password),
            onClick = onRestoreClick
        )
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
    }
}

private fun containerShape(curveHeightPx: Float) = GenericShape { size, _ ->
    val w = size.width
    val h = size.height
    moveTo(0f, curveHeightPx)
    quadraticTo(x1 = w / 4f, y1 = 0f, x2 = w, y2 = curveHeightPx)
    lineTo(w, h)
    lineTo(0f, h)
    close()
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    Theme {
        SignIn(
            modifier = Modifier,
            emailTextState = TextFieldState("alexander@gmail.com"),
            state = SignInState.default().copy(
                emailError = "Email error",
                passwordError = "Password error",
            ),
            passwordTextState = TextFieldState("123456"),
            onLoggedIn = {},
            onSignUpClick = {},
            onRestoreClick = {},
            shapeSize = Theme.dimens.triplePad
        )
    }
}
