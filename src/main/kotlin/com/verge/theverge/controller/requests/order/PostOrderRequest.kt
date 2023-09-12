package com.verge.theverge.controller.requests.order

import com.fasterxml.jackson.annotation.JsonAlias
import com.verge.theverge.models.TableModel
import java.time.LocalTime

data class PostOrderRequest(

    @JsonAlias("payment_status")
    val paymentStatus: Boolean? = false,

    @JsonAlias("tables_id")
    val table: Int

)
