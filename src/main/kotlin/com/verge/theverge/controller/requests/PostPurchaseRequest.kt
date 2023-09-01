package com.verge.theverge.controller.requests

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull

data class PostPurchaseRequest (
    @field:NotNull
    @field:Positive
    @JsonAlias("tables_id")
    val tableId: Int,

    @field:NotNull
    @JsonAlias("items_id")
    val itemsIds: Set<Int>,

)
