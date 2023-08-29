package com.verge.theverge.controller.requests.order

import java.time.LocalTime

data class PutOrderRequest(
    val schedule: LocalTime?,
    )
