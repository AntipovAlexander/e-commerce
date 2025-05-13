package com.ecommerce.data.core.resources

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.BoolRes
import androidx.annotation.ColorRes
import androidx.annotation.IntegerRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.ecommerce.domain.core.resources.ResourceProvider

/**
 * This class is used to get resources in context-aware places (e.g. viewmodels, presenters)
 */
class AndroidResourceProvider(private val context: Context) : ResourceProvider {

    override fun getString(@StringRes resourceIdentifier: Int, vararg arguments: Any): String {
        return if (arguments.isNotEmpty())
            context.resources.getString(resourceIdentifier, *arguments)
        else
            context.resources.getString(resourceIdentifier)
    }

    override fun getQuantityString(@PluralsRes resourceIdentifier: Int, quantity: Int, vararg arguments: Any): String {
        return if (arguments.isNotEmpty())
            context.resources.getQuantityString(resourceIdentifier, quantity, *arguments)
        else
            context.resources.getQuantityString(resourceIdentifier, quantity)
    }

    override fun getStringArray(@ArrayRes resourceIdentifier: Int): Array<String> =
        context.resources.getStringArray(resourceIdentifier)

    override fun getInteger(@IntegerRes resourceIdentifier: Int): Int =
        context.resources.getInteger(resourceIdentifier)

    override fun getIntegerArray(@ArrayRes resourceIdentifier: Int): Array<Int> =
        context.resources.getIntArray(resourceIdentifier).toTypedArray()

    override fun getBoolean(@BoolRes resourceIdentifier: Int): Boolean =
        context.resources.getBoolean(resourceIdentifier)

    override fun getColor(@ColorRes resourceIdentifier: Int): Int =
        ContextCompat.getColor(context, resourceIdentifier)

    override fun getDimen(resourceIdentifier: Int): Float =
        context.resources.getDimension(resourceIdentifier)
}
