package com.verge.theverge.models

import jakarta.persistence.*
import java.math.BigDecimal


@Entity(name="invoice")
data class InvoiceModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "invoice_id")
    var id: Int? = null,

    @Column(name = "total")
    var total: BigDecimal? = null,

    @OneToOne
    @JoinColumn(name="order_id")
    var order: OrderModel? = null,

    @OneToOne
    @JoinColumn(name = "orderitem_id")
    var orderitem: OrderItemModel? = null

)
