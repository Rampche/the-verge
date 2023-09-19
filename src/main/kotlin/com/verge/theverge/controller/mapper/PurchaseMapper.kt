package com.verge.theverge.controller.mapper

import com.verge.theverge.controller.requests.purchase.PostPurchaseRequest
import com.verge.theverge.models.PurchaseModel
import com.verge.theverge.services.ItemsService
import com.verge.theverge.services.TablesService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val itemsService: ItemsService,
    private val tablesService: TablesService
) {

fun toModel(request: PostPurchaseRequest):PurchaseModel{
    val table = tablesService.findTableById(request.tableId)
    val items = itemsService.findAllById(request.itemsIds)

    return PurchaseModel(
        table = table,
        items = items.toMutableList(),
        price = items.sumOf { it.price
        }
    )
}
}