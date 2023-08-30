package com.verge.theverge.controller.requests.employee

import com.verge.theverge.enums.EmployeeStatus
import com.verge.theverge.enums.RoleType

data class PostEmployeeRequest (
    val name: String,
    val email: String,
    val password: String,
    val role: RoleType,
    val status: EmployeeStatus
)