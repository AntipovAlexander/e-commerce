package com.ecommerce.details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    text: String
) {
    val count by viewModel.count
    ProductDetails(text, count, viewModel::increment)
}

@Composable
internal fun ProductDetails(text: String, count: Int, onIncrement: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Product details")
        Spacer(modifier = Modifier.height(20.dp))
        Text("Came from: $text")
        Spacer(modifier = Modifier.height(20.dp))
        Text("Count is: $count")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onIncrement) { Text("Increment") }
    }
}

@Preview(showBackground = true)
@Suppress("MagicNumber")
@Composable
private fun ProfilePreview() {
    ProductDetails(text = "Preview", count = 101, onIncrement = {})
}
