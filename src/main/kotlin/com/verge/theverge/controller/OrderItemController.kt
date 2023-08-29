package com.verge.theverge.controller


import com.verge.theverge.controller.requests.orderitem.PostOrderItemRequest
import com.verge.theverge.controller.requests.orderitem.PutOrderItemRequest
import com.verge.theverge.extensions.toOrderItemModel
import com.verge.theverge.models.OrderItemModel
import com.verge.theverge.services.ItemsService
import com.verge.theverge.services.OrderItemService
import com.verge.theverge.services.OrderService
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
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/order-items")
class OrderItemController(
    val orderItemService: OrderItemService,
    val itemService: ItemsService,
    val orderService: OrderService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrderItem(@RequestBody orderItem: PostOrderItemRequest){
        val items = itemService.findItemById(orderItem.items)
        orderItemService.createOrderItem(orderItem.toOrderItemModel(items))
    }

    @GetMapping
    fun findOrderItem(): List<OrderItemModel>{
        return orderItemService.findOrderItem()
    }

    @GetMapping("/{id}")
    fun findOrderItemById(@PathVariable id: Int):OrderItemModel{
        return orderItemService.findOrderItemById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateOrderItem(@PathVariable id: Int, @RequestBody orderItem: PutOrderItemRequest){
        val orderItemSaved = orderItemService.findOrderItemById(id)
        val itemSaved = itemService.findItemById(id)
        //val orderSaved = orderService.findOrderById(orderItem.order!!)
            orderItemService.updateOrderItem(orderItem.toOrderItemModel(orderItemSaved, itemSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrderItem(@PathVariable id: Int){
        orderItemService.deleteOrderItem(id)
    }
}