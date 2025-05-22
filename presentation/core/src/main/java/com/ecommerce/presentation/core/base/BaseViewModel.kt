package com.ecommerce.presentation.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State : Reducer.ViewState, Intent : Reducer.ViewIntent, Effect : Reducer.ViewEffect>(
    initialState: State,
    private val reducer: Reducer<State, Intent, Effect>
) : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    private val _effects = Channel<Effect>(capacity = Channel.CONFLATED)
    val effect = _effects.receiveAsFlow()

    fun applyEffect(effect: Effect) {
        _effects.trySend(effect)
    }

    fun applyIntent(intent: Intent) {
        val (newState, effect) = reducer.reduce(_state.value, intent)
        _state.tryEmit(newState)
        effect?.let(::applyEffect)
    }
}
