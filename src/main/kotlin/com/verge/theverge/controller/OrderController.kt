package com.verge.theverge.controller

import com.verge.theverge.controller.requests.order.PostOrderRequest
import com.verge.theverge.controller.requests.order.PutOrderRequest
import com.verge.theverge.events.OrderEvent
import com.verge.theverge.extensions.toOrderModel
import com.verge.theverge.models.OrderModel
import com.verge.theverge.services.*
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(
    val orderService: OrderService,
    val tablesService: TablesService,
    ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody order: PostOrderRequest){
        val table = tablesService.findTableById(order.table)
        orderService.createOrder(order.toOrderModel(table))
    }

    @GetMapping
    fun findOrders(): List<OrderModel>{
        return orderService.findOrders()
    }

    @GetMapping("/{id}")
    fun findOrderById(@PathVariable id: Int):OrderModel{
        return orderService.findOrderById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateOrder(@PathVariable id: Int, @RequestBody order: PutOrderRequest){
        val orderSaved = orderService.findOrderById(id)
        val tableSaved = tablesService.findTableById(order.table!!) //Consigo alterar
        orderService.updateOrder(order.toOrderModel((tableSaved), orderSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrder(@PathVariable id: Int){
        orderService.deleteOrder(id)
    }
}