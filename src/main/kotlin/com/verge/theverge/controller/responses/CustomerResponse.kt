package com.verge.theverge.controller.responses

import com.verge.theverge.enums.CustomerStatus

data class CustomerResponse (
    val id: Int,
    val name: String,
    val email: String,
    val status: CustomerStatus
)