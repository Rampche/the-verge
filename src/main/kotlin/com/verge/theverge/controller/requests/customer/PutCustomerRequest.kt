package com.verge.theverge.controller.requests.customer

data class PutCustomerRequest(
    var name: String?,
    var email: String?,
    var password: String?
)
