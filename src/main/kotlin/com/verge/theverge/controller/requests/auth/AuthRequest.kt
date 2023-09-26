package com.verge.theverge.controller.requests.auth

import com.fasterxml.jackson.annotation.JsonAlias

data class AuthRequest(

    @JsonAlias("username")
    val username: String,

    val password: String

)
