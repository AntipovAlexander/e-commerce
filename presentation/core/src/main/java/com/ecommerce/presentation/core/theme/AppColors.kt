package com.ecommerce.presentation.core.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ecommerce.presentation.core.R

private const val ALPHA_DISABLED = 0.7f

data class AppColors(
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val backgroundAccent: Color,
    val contentPrimary: Color,
    val contentSecondary: Color,
    val contentTertiary: Color,
    val contentAccent: Color,
    val contentStateDisabled: Color,
    val contentOnColorInverse: Color,
    val contentSale: Color
) {
    val backgroundAccentDisabled
        get() = backgroundAccent.copy(alpha = ALPHA_DISABLED)
    val contentOnColorInverseDisabled
        get() = contentOnColorInverse.copy(alpha = ALPHA_DISABLED)
    val contentPrimaryDisabled
        get() = contentPrimary.copy(alpha = ALPHA_DISABLED)
}

val LightColorScheme
    @Composable
    get() = AppColors(
        backgroundPrimary = colorResource(R.color.white),
        backgroundSecondary = colorResource(R.color.gray50),
        backgroundTertiary = colorResource(R.color.gray700),
        backgroundAccent = colorResource(R.color.gray900),
        contentPrimary = colorResource(R.color.gray900),
        contentSecondary = colorResource(R.color.gray600),
        contentTertiary = colorResource(R.color.gray500),
        contentAccent = colorResource(R.color.gray900),
        contentStateDisabled = colorResource(R.color.gray200),
        contentOnColorInverse = colorResource(R.color.gray100),
        contentSale = colorResource(R.color.red600)
    )

val DarkColorScheme
    @Composable
    get() = AppColors(
        backgroundPrimary = colorResource(R.color.gray900),
        backgroundSecondary = colorResource(R.color.gray700),
        backgroundTertiary = colorResource(R.color.gray600),
        backgroundAccent = colorResource(R.color.gray200),
        contentPrimary = colorResource(R.color.white),
        contentSecondary = colorResource(R.color.gray200),
        contentTertiary = colorResource(R.color.gray500),
        contentAccent = colorResource(R.color.white),
        contentStateDisabled = colorResource(R.color.gray600),
        contentOnColorInverse = colorResource(R.color.gray900),
        contentSale = colorResource(R.color.red600)
    )

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        backgroundPrimary = UNSPECIFIED,
        backgroundSecondary = UNSPECIFIED,
        backgroundTertiary = UNSPECIFIED,
        backgroundAccent = UNSPECIFIED,
        contentPrimary = UNSPECIFIED,
        contentSecondary = UNSPECIFIED,
        contentTertiary = UNSPECIFIED,
        contentAccent = UNSPECIFIED,
        contentStateDisabled = UNSPECIFIED,
        contentOnColorInverse = UNSPECIFIED,
        contentSale = UNSPECIFIED
    )
}

private val UNSPECIFIED = Color(0x00000000)

@Composable
private fun ColorSwatch(name: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Theme.dimens.halfPad)
    ) {
        Box(
            modifier = Modifier
                .size(Theme.dimens.triplePad)
                .border(1.dp, Color.Black)
                .background(color)
        )
        androidx.compose.material3.Text(
            text = name,
            style = LocalAppTypography.current.body.one.regular,
            modifier = Modifier.padding(start = Theme.dimens.singlePad)
        )
    }
}

@Composable
private fun ColorsList() {
    val colors = LocalAppColors.current
    Column(
        verticalArrangement = Arrangement.spacedBy(Theme.dimens.halfPad),
        modifier = Modifier.padding(Theme.dimens.doublePad)
    ) {
        ColorSwatch("backgroundPrimary", colors.backgroundPrimary)
        ColorSwatch("backgroundSecondary", colors.backgroundSecondary)
        ColorSwatch("backgroundTertiary", colors.backgroundTertiary)
        ColorSwatch("backgroundAccent", colors.backgroundAccent)
        ColorSwatch("contentPrimary", colors.contentPrimary)
        ColorSwatch("contentSecondary", colors.contentSecondary)
        ColorSwatch("contentTertiary", colors.contentTertiary)
        ColorSwatch("contentAccent", colors.contentAccent)
        ColorSwatch("contentStateDisabled", colors.contentStateDisabled)
        ColorSwatch("contentOnColorInverse", colors.contentOnColorInverse)
        ColorSwatch("contentSale", colors.contentSale)
    }
}

@Preview(showBackground = true, name = "Light Colors")
@Composable
private fun ColorsPreview_Light() {
    CompositionLocalProvider(LocalAppColors provides LightColorScheme) {
        ColorsList()
    }
}

@Preview(showBackground = true, name = "Dark Colors")
@Composable
private fun ColorsPreview_Dark() {
    CompositionLocalProvider(LocalAppColors provides DarkColorScheme) {
        ColorsList()
    }
}
