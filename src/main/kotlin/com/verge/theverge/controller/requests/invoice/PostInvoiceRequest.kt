package com.verge.theverge.controller.requests.invoice

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

data class PostInvoiceRequest(

    @JsonAlias("order_id")
    val order: Int,

    @JsonAlias("orderitem_id")
    val orderitem: Int,

    val total: BigDecimal?,


)