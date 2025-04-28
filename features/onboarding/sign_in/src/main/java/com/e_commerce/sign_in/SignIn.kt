package com.e_commerce.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.e_commerce.core.ui.widgets.buttons.PrimaryButton
import com.e_commerce.core.ui.widgets.inputs.PrimaryInput

@Composable
fun SignInScreen(
    onSignUpClicked: () -> Unit,
    onRestoreClicked: () -> Unit,
    onLoggedIn: () -> Unit
) {
    var enteredEmail by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SIGN IN",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryInput(
            text = enteredEmail,
            placeholder = "Test",
            iconRes = R.drawable.ic_search
        ) { enteredEmail = it }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an account?")
            Spacer(modifier = Modifier.width(8.dp))
            PrimaryButton(text = "Sign up", onClick = onSignUpClicked)
            PrimaryButton(text = "Let me in", onClick = onLoggedIn)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Forgot password?")
            Spacer(modifier = Modifier.width(8.dp))
            PrimaryButton(text = "Restore", onClick = onRestoreClicked)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(onSignUpClicked = {}, onRestoreClicked = {}, onLoggedIn = {})
}