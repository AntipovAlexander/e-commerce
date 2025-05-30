package com.ecommerce.presentation.core.model

import com.ecommerce.presentation.core.R

sealed class NotificationMessage(open val text: String) {
    abstract val bgColor: Int
    abstract val textColor: Int

    data class Error(override val text: String) : NotificationMessage(text) {
        override val bgColor: Int = R.color.red300
        override val textColor: Int = R.color.red600
    }

    data class Success(override val text: String) : NotificationMessage(text) {
        override val bgColor: Int = R.color.green300
        override val textColor: Int = R.color.green600
    }
}
