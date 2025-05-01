package com.e_commerce.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.e_commerce.core.ui.R

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
)

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
