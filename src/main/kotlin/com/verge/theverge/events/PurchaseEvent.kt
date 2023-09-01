package com.verge.theverge.events

import com.verge.theverge.models.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent (
    source: Any,
    val purchaseModel: PurchaseModel
):ApplicationEvent(source)



