package com.verge.theverge.controller.requests.customer

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty
    val name: String,

    @field:Email
    val email: String,

    val password: String
)