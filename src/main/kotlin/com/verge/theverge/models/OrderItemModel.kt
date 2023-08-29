package com.verge.theverge.models

import jakarta.persistence.*

@Entity(name="orderItem")
data class OrderItemModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "orderitem_id")
    var id: Int? = null,

    @ManyToOne
    @JoinColumn(name="item_id")
    var items: ItemsModel? = null,

    var quantity: Int

//Para cada item_id dentro de orderItem, preciso multiplicar a quantidade de items pelo valor de cada item em ItemsModel

)

