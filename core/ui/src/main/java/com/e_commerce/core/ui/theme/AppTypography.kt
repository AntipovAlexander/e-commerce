@file:OptIn(ExperimentalTextApi::class)

package com.e_commerce.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.e_commerce.core.ui.R

private fun font(
    size: TextUnit,
    weight: FontWeight,
    decoration: TextDecoration? = null,
    height: TextUnit = TextUnit.Unspecified
) = TextStyle(
    fontFamily = FontFamily(
        Font(
            resId = R.font.inter_variable_font_opsz_wght,
            variationSettings = FontVariation.Settings(FontVariation.weight(400)),
            weight = FontWeight.Normal
        ),
        Font(
            R.font.inter_variable_font_opsz_wght,
            variationSettings = FontVariation.Settings(FontVariation.weight(500)),
            weight = FontWeight.Medium
        ),
        Font(
            R.font.inter_variable_font_opsz_wght,
            variationSettings = FontVariation.Settings(FontVariation.weight(600)),
            weight = FontWeight.SemiBold
        ),
        Font(
            R.font.inter_variable_font_opsz_wght,
            variationSettings = FontVariation.Settings(FontVariation.weight(700)),
            weight = FontWeight.Bold
        ),
        Font(
            R.font.inter_variable_font_opsz_wght,
            variationSettings = FontVariation.Settings(FontVariation.weight(800)),
            weight = FontWeight.ExtraBold
        )
    ),
    fontWeight = weight,
    fontSize = size,
    lineHeight = height,
    textDecoration = decoration
)

data class AppTypography(
    val heading: Heading,
    val body: Body,
    val caption: Caption
) {
    data class Heading(
        val one: One,
        val two: Two,
        val three: Three
    ) {
        data class One(
            val semiBold: TextStyle
        )

        data class Two(
            val extraBold: TextStyle,
            val semiBold: TextStyle,
            val strikethrough: TextStyle
        )

        data class Three(
            val extraBold: TextStyle,
            val bold: TextStyle,
            val medium: TextStyle
        )
    }

    data class Body(
        val one: One,
        val two: Two,
        val three: Three
    ) {
        data class One(
            val regular: TextStyle,
            val underline: TextStyle
        )

        data class Two(
            val medium: TextStyle,
            val bold: TextStyle,
            val strikethrough: TextStyle
        )

        data class Three(
            val regular: TextStyle
        )
    }

    data class Caption(
        val one: One,
        val two: Two
    ) {
        data class One(
            val regular: TextStyle
        )

        data class Two(
            val semiBold: TextStyle
        )
    }
}

val defaultTypography = AppTypography(
    heading = AppTypography.Heading(
        one = AppTypography.Heading.One(
            semiBold = font(
                size = 32.sp,
                weight = FontWeight.SemiBold
            )
        ),
        two = AppTypography.Heading.Two(
            extraBold = font(
                size = 20.sp,
                weight = FontWeight.ExtraBold,
                height = 20.sp
            ),
            semiBold = font(
                size = 20.sp,
                weight = FontWeight.SemiBold,
                height = 20.sp
            ),
            strikethrough = font(
                size = 20.sp,
                weight = FontWeight.Normal,
                decoration = TextDecoration.LineThrough,
                height = 20.sp
            )
        ),
        three = AppTypography.Heading.Three(
            extraBold = font(
                size = 14.sp,
                weight = FontWeight.ExtraBold,
                height = 14.sp
            ),
            bold = font(
                size = 14.sp,
                weight = FontWeight.Bold
            ),
            medium = font(
                size = 14.sp,
                weight = FontWeight.Medium,
                height = 16.sp
            )
        )
    ),
    body = AppTypography.Body(
        one = AppTypography.Body.One(
            regular = font(
                size = 14.sp,
                weight = FontWeight.Normal,
                height = 20.sp
            ),
            underline = font(
                size = 14.sp,
                weight = FontWeight.Normal,
                height = 20.sp,
                decoration = TextDecoration.Underline
            )
        ),
        two = AppTypography.Body.Two(
            medium = font(
                size = 12.sp,
                weight = FontWeight.Medium,
                height = 14.sp
            ),
            bold = font(
                size = 12.sp,
                weight = FontWeight.Bold,
                height = 14.sp
            ),
            strikethrough = font(
                size = 12.sp,
                weight = FontWeight.Normal,
                decoration = TextDecoration.LineThrough,
                height = 14.sp
            )
        ),
        three = AppTypography.Body.Three(
            regular = font(
                size = 16.sp,
                weight = FontWeight.Normal,
                height = 16.sp
            )
        )
    ),
    caption = AppTypography.Caption(
        one = AppTypography.Caption.One(
            regular = font(
                size = 10.sp,
                weight = FontWeight.Normal,
                height = 12.sp
            )
        ),
        two = AppTypography.Caption.Two(
            semiBold = font(
                size = 10.sp,
                weight = FontWeight.SemiBold
            )
        )
    )
)

val LocalAppTypography = staticCompositionLocalOf { defaultTypography }
