package com.verge.theverge.controller.requests.order

import com.fasterxml.jackson.annotation.JsonAlias
import com.verge.theverge.models.TableModel
import java.time.LocalTime

data class PutOrderRequest(
    @JsonAlias("payment_status")
    val paymentStatus: Boolean?,

    @JsonAlias("table_id")
    val table: Int?
    )
