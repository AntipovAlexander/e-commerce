package com.ecommerce.presentation.core.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map

fun Flow<CharSequence>.observeAsText() = drop(1).map { it.toString() }.distinctUntilChanged()
