package com.e_commerce.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignInScreen(
    onSignUpClicked: () -> Unit,
    onRestoreClicked: () -> Unit,
    onLoggedIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "Sign in" text at the top
        Text(
            text = "SIGN IN",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Row with "Don't have an account?" text and "Sign up" button
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an account?")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onSignUpClicked) {
                Text("Sign up")
            }
            Button(onClick = onLoggedIn) {
                Text("Let me in")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Row with "Forgot password?" text and "Restore" button
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Forgot password?")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onRestoreClicked) {
                Text("Restore")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(onSignUpClicked = {}, onRestoreClicked = {}, onLoggedIn = {})
}