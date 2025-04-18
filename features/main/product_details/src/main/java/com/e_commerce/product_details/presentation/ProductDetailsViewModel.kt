package com.e_commerce.product_details.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class ProductDetailsViewModel : ViewModel() {

    init {
        Log.d("viewmodel-debug", "Created Vm: $this")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("viewmodel-debug", "Destroyed: $this")
    }

    private val _count = mutableIntStateOf(0)
    val count: State<Int> = _count

    fun increment() {
        _count.intValue += 1
    }
}