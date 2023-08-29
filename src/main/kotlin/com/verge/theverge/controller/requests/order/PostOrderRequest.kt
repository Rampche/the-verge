package com.verge.theverge.controller.requests.order

import com.fasterxml.jackson.annotation.JsonAlias
import java.time.LocalTime

data class PostOrderRequest(
    val schedule: LocalTime,

    @JsonAlias("reservation_id")
    val reservation: Int,

    @JsonAlias("employee_id")
    val employee: Int,

)
