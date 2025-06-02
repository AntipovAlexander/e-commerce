package com.ecommerce.data.core.network

import com.ecommerce.data.core.model.ErrorModel

class CustomClientErrorException(val body: ErrorModel) : Exception(body.message)
