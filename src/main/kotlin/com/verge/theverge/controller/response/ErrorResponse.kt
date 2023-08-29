package com.verge.theverge.controller.response

import org.springframework.validation.FieldError

data class ErrorResponse(
    var httpCode: Int,
    var message: String,
    var internalCode: String,
    var errors: List <FieldErrorResponse>?
)
