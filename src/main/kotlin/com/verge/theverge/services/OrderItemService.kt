package com.verge.theverge.services

import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.NotFoundException
import com.verge.theverge.models.OrderItemModel
import com.verge.theverge.repository.*
import org.springframework.stereotype.Service

@Service
class OrderItemService(
    val orderItemRepository: OrderItemRepository
    ) {

    //Create a orderitem
    fun createOrderItem(orderItem: OrderItemModel){
        orderItemRepository.save(orderItem)
    }

    //List the orderitem
    fun findOrderItem(): List<OrderItemModel>{
        return orderItemRepository.findAll().toList()
    }

    //List orderitem by id
    fun findOrderItemById(id: Int):OrderItemModel{
        return orderItemRepository.findById(id).orElseThrow { NotFoundException(Errors.VG701.message.format(id), Errors.VG701.code) }
    }

    //Update the OrderItem
    fun updateOrderItem(orderItem: OrderItemModel){
        if (!orderItemRepository.existsById(orderItem.id!!)){
            throw NotFoundException(Errors.VG701.message.format(orderItem.id), Errors.VG701.code)
        }
        orderItemRepository.save(orderItem)
    }

    //Delete the OrderItem
    fun deleteOrderItem(id: Int){
        if (!orderItemRepository.existsById(id)){
          throw NotFoundException(Errors.VG701.message.format(id), Errors.VG701.code)
        }
        orderItemRepository.deleteById(id)
    }


}