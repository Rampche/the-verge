package com.verge.theverge.events

import com.verge.theverge.models.OrderModel
import org.springframework.context.ApplicationEvent

class OrderEvent (
    source: Any,
    val orderModel: OrderModel
):ApplicationEvent(source)



