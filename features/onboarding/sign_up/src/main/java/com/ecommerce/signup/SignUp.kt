package com.ecommerce.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ecommerce.core.ui.theme.Theme
import com.ecommerce.core.ui.widgets.buttons.PrimaryButton
import com.ecommerce.core.ui.widgets.inputs.EmailInput
import com.ecommerce.core.ui.widgets.inputs.PasswordInput

private const val IMAGE_CONTAINER_RATIO = 0.5f
private const val BUTTONS_CONTAINER_RATIO = 0.6f

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shapeSize = Theme.dimens.triplePad
    val emailTextState = rememberTextFieldState()
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(IMAGE_CONTAINER_RATIO)
                .align(Alignment.TopCenter),
            painter = painterResource(R.drawable.sign_up_top_image),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
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
            val enterPasswordState = remember { TextFieldState() }
            val repeatPasswordState = remember { TextFieldState() }
            Spacer(modifier = Modifier.height(shapeSize + Theme.dimens.doublePad))
            Text(
                text = stringResource(R.string.lets_get_started),
                style = Theme.typography.heading.one.semiBold,
                color = Theme.colors.contentPrimary
            )
            Spacer(modifier = Modifier.height(Theme.dimens.doublePad))
            EmailInput(
                state = emailTextState,
                placeholder = stringResource(R.string.your_email)
            )
            Spacer(modifier = Modifier.height(Theme.dimens.singlePad))
            PasswordInput(
                state = enterPasswordState,
                placeholder = stringResource(R.string.set_your_password)
            )
            Spacer(modifier = Modifier.height(Theme.dimens.singlePad))
            PasswordInput(
                state = repeatPasswordState,
                placeholder = stringResource(R.string.repeat_your_password)
            )
            PrimaryButton(text = stringResource(R.string.sign_up), onClick = onSignUpClick)
        }
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
    SignUpScreen(onSignUpClick = {})
}
