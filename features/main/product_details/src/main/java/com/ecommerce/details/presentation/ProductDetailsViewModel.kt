package com.ecommerce.details.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ProductDetailsViewModel : ViewModel() {

    init {
        Timber.d("Created Vm: $this")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("Destroyed: $this")
    }

    private val _count = mutableIntStateOf(0)
    val count: State<Int> = _count

    fun increment() {
        _count.intValue += 1
    }
}
