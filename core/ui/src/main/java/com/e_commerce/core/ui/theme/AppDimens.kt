package com.e_commerce.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
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
    val triplePad: Dp
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
    triplePad = 24.dp
)

val LocalAppDimens = staticCompositionLocalOf { defaultDimens }