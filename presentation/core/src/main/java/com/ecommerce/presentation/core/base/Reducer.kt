package com.ecommerce.presentation.core.base

interface Reducer<State : Reducer.ViewState, Intent : Reducer.ViewIntent, Effect : Reducer.ViewEffect> {
    interface ViewState

    interface ViewIntent

    interface ViewEffect

    fun reduce(previousState: State, intent: Intent): Pair<State, Effect?>
}
