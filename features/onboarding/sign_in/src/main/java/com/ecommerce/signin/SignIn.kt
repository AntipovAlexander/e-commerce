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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.ecommerce.core.ui.theme.Theme
import com.ecommerce.core.ui.widgets.buttons.PrimaryButton
import com.ecommerce.core.ui.widgets.buttons.TextButton
import com.ecommerce.core.ui.widgets.inputs.PrimaryInput

private const val IMAGE_CONTAINER_RATIO = 0.5f
private const val BUTTONS_CONTAINER_RATIO = 0.6f

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onRestoreClick: () -> Unit,
    onLoggedIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shapeSize = Theme.dimens.triplePad
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
        ButtonsContainer(shapeSize, onLoggedIn, onSignUpClick, onRestoreClick)
    }
}

@Composable
private fun BoxScope.ButtonsContainer(
    shapeSize: Dp,
    onLoggedIn: () -> Unit,
    onSignUpClick: () -> Unit,
    onRestoreClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(BUTTONS_CONTAINER_RATIO)
            .align(Alignment.BottomCenter)
            .clip(containerShape(with(LocalDensity.current) { shapeSize.toPx() }))
            .background(color = Theme.colors.backgroundPrimary)
            .padding(horizontal = Theme.dimens.doublePad),
        verticalArrangement = Arrangement.Top
    ) {
        var enteredEmail by rememberSaveable { mutableStateOf("") }
        var enteredPassword by rememberSaveable { mutableStateOf("") }
        Spacer(modifier = Modifier.height(shapeSize + Theme.dimens.doublePad))
        Text(
            text = stringResource(R.string.welcome_back),
            style = Theme.typography.heading.one.semiBold,
            color = Theme.colors.contentPrimary
        )
        Spacer(modifier = Modifier.height(Theme.dimens.doublePad))
        PrimaryInput(
            text = enteredEmail,
            onChange = { enteredEmail = it },
            placeholder = stringResource(R.string.your_email)
        )
        Spacer(modifier = Modifier.height(Theme.dimens.singlePad))
        PrimaryInput(
            text = enteredPassword,
            onChange = { enteredPassword = it },
            placeholder = stringResource(R.string.your_password)
        )
        Spacer(modifier = Modifier.height(Theme.dimens.singlePad))
        PrimaryButton(
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
    SignInScreen(onSignUpClick = {}, onRestoreClick = {}, onLoggedIn = {})
}
