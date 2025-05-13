package com.ecommerce.presentation.core.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimens(
    val eighthPad: Dp,
    val quarterPad: Dp,
    val halfPad: Dp,
    val threeQuarterPad: Dp,
    val singlePad: Dp,
    val singleQuarterPad: Dp,
    val singleHalfPad: Dp,
    val singleThreeQuarterPad: Dp,
    val doublePad: Dp,
    val triplePad: Dp,
    val sixPad: Dp
)

val defaultDimens = AppDimens(
    eighthPad = 1.dp,
    quarterPad = 2.dp,
    halfPad = 4.dp,
    threeQuarterPad = 6.dp,
    singlePad = 8.dp,
    singleQuarterPad = 10.dp,
    singleHalfPad = 12.dp,
    singleThreeQuarterPad = 14.dp,
    doublePad = 16.dp,
    triplePad = 24.dp,
    sixPad = 48.dp
)

val LocalAppDimens = staticCompositionLocalOf { defaultDimens }

@Preview(showBackground = true)
@Composable
private fun DimensPreviews() {
    Theme {
        val d = LocalAppDimens.current
        Column(modifier = Modifier.padding(16.dp)) {
            listOf(
                "eighthPad" to d.eighthPad,
                "quarterPad" to d.quarterPad,
                "halfPad" to d.halfPad,
                "threeQuarterPad" to d.threeQuarterPad,
                "singlePad" to d.singlePad,
                "singleQuarterPad" to d.singleQuarterPad,
                "singleHalfPad" to d.singleHalfPad,
                "singleThreeQuarterPad" to d.singleThreeQuarterPad,
                "doublePad" to d.doublePad,
                "triplePad" to d.triplePad,
                "sixPad" to d.sixPad
            ).forEach { (name, size) ->
                Text(
                    text = "$name: ${size.value}dp",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Box(
                    modifier = Modifier
                        .width(size)
                        .height(4.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}
