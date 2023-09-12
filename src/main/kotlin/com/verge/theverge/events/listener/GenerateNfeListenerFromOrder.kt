package com.verge.theverge.events.listener

import com.verge.theverge.events.OrderEvent
import com.verge.theverge.services.OrderService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListenerFromOrder(
    private val orderService: OrderService
) {
    @Async
    @EventListener
    fun listen(orderEvent: OrderEvent){
        val nfe = UUID.randomUUID().toString()
        val orderModel = orderEvent.orderModel.copy(nfe = nfe)
        orderService.updateOrder(orderModel)
    }
}