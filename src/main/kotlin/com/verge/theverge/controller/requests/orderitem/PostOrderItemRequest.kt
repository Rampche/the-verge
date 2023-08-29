package com.verge.theverge.controller.requests.orderitem

import com.fasterxml.jackson.annotation.JsonAlias

data class PostOrderItemRequest(
    val quantity: Int,

    @JsonAlias("item_id")
    val items: Int

)
