@file:OptIn(ExperimentalTextApi::class)
@file:Suppress("MagicNumber")

package com.ecommerce.presentation.core.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecommerce.presentation.core.R

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

@Preview(showBackground = true)
@Composable
private fun Typography_Preview_Headings() {
    Theme {
        val heading = LocalAppTypography.current.heading
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Heading One SemiBold", style = heading.one.semiBold)
            Text("Heading Two ExtraBold", style = heading.two.extraBold)
            Text("Heading Two SemiBold", style = heading.two.semiBold)
            Text("Heading Two Strikethrough", style = heading.two.strikethrough)
            Text("Heading Three ExtraBold", style = heading.three.extraBold)
            Text("Heading Three Bold", style = heading.three.bold)
            Text("Heading Three Medium", style = heading.three.medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Typography_Preview_Bodies() {
    Theme {
        val body = LocalAppTypography.current.body
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Body One Regular", style = body.one.regular)
            Text("Body One Underline", style = body.one.underline)
            Text("Body Two Medium", style = body.two.medium)
            Text("Body Two Bold", style = body.two.bold)
            Text("Body Two Strikethrough", style = body.two.strikethrough)
            Text("Body Three Regular", style = body.three.regular)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Typography_Preview_Captions() {
    Theme {
        val caption = LocalAppTypography.current.caption
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Caption One Regular", style = caption.one.regular)
            Text("Caption Two SemiBold", style = caption.two.semiBold)
        }
    }
}
