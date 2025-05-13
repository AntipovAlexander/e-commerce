package com.ecommerce.forgot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ecommerce.presentation.core.theme.LocalAppDimens

@Composable
fun ForgotPasswordScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(LocalAppDimens.current.doublePad),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "Sign in" text at the top
        Text(
            text = "Forgot Password?",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(LocalAppDimens.current.triplePad))

        // Row with "Don't have an account?" text and "Sign up" button
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onBackClick) {
                Text("Back")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ForgotPasswordPreview() {
    ForgotPasswordScreen(onBackClick = {})
}
