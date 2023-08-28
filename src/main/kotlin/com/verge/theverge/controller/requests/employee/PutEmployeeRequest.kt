package com.verge.theverge.controller.requests.employee

import com.verge.theverge.enums.RoleType

data class PutEmployeeRequest(
    var name: String?,
    var email: String?,
    var password: String?,
    var role: RoleType?
)
