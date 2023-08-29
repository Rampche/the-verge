package com.verge.theverge.repository


import com.verge.theverge.models.OrderItemModel
import org.springframework.data.repository.CrudRepository

interface OrderItemRepository:CrudRepository<OrderItemModel, Int> {

}