package com.verge.theverge.events.listener

import com.verge.theverge.events.PurchaseEvent
import com.verge.theverge.services.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
    private val purchaseService: PurchaseService
) {
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        val nfe = UUID.randomUUID().toString()
        val purchaseModel = purchaseEvent.purchaseModel.copy(nfe = nfe)
        purchaseService.updatePurchase(purchaseModel)
    }
}