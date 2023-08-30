package com.verge.theverge.services

import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.NotFoundException
import com.verge.theverge.models.OrderModel
import com.verge.theverge.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    val orderRepository: OrderRepository,
) {

    //Create a new order
    fun createOrder(order: OrderModel){
        orderRepository.save(order)
    }

    //List the orders
    fun findOrders(): List<OrderModel> {
        return orderRepository.findAll().toList()
    }

    //List order by Id
    fun findOrderById(id: Int): OrderModel{
        if (!orderRepository.existsById(id)){
            throw NotFoundException(Errors.VG501.message.format(id), Errors.VG501.code)
        }
        return orderRepository.findById(id).orElseThrow()
    }

    //Update order
    fun updateOrder(order: OrderModel) {
        if (!orderRepository.existsById(order.id!!)){
            throw NotFoundException(Errors.VG501.message.format(order.id), Errors.VG501.code)
        }
        orderRepository.save(order)
    }

    //Delete Order
    fun deleteOrder(id: Int) {
        if (!orderRepository.existsById(id)){
            throw NotFoundException(Errors.VG501.message.format(id), Errors.VG501.code)
        }
        orderRepository.deleteById(id)
    }
}