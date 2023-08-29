package com.verge.theverge.repository

import com.verge.theverge.models.OrderModel
import org.springframework.data.repository.CrudRepository

interface OrderRepository:CrudRepository<OrderModel, Int> {
}