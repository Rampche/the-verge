package com.verge.theverge.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "orders")
data class OrderModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "orders_id")
    var id: Int? = null,

    @Column(name="nfe")
    val nfe: String? = null,

    @Column(name="payment_status")
    val paymentStatus: Boolean? = false,

    @ManyToOne
    @JoinColumn(name = "tables_id")
    var table: TableModel? = null,

)
