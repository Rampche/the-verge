package com.verge.theverge.controller.requests.orderitem

import com.fasterxml.jackson.annotation.JsonAlias

data class PutOrderItemRequest(
    val quantity: Int?,

    @JsonAlias("order_id")
    val order: Int?,

    @JsonAlias("item_id")
    val items: Int?

    )
