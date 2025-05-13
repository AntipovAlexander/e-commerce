package com.ecommerce.presentation.core.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class AppShapes(
    val small: Shape,
    val default: Shape,
    val large: Shape
)

val defaultShapes = AppShapes(
    small = RoundedCornerShape(size = 8.dp),
    default = RoundedCornerShape(size = 12.dp),
    large = RoundedCornerShape(size = 24.dp)
)

val LocalAppShapes = staticCompositionLocalOf { defaultShapes }

@Preview(showBackground = true, name = "Shapes Preview")
@Composable
private fun ShapesPreviews() {
    Theme {
        val shapes = LocalAppShapes.current
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf(
                "small" to shapes.small,
                "default" to shapes.default,
                "large" to shapes.large
            ).forEach { (name, shape) ->
                Text(
                    text = name,
                    style = LocalAppTypography.current.body.one.regular
                )
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(shape)
                        .background(LocalAppColors.current.contentAccent)
                )
            }
        }
    }
}
