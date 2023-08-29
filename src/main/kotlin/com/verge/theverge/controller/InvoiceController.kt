package com.verge.theverge.controller



import com.verge.theverge.controller.requests.invoice.PostInvoiceRequest
import com.verge.theverge.controller.requests.invoice.PutInvoiceRequest
import com.verge.theverge.extensions.toInvoiceModel
import com.verge.theverge.models.InvoiceModel
import com.verge.theverge.services.*
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
@RequestMapping("/invoices")
class InvoiceController(
    val invoiceService: InvoiceService,
    val orderService: OrderService,
    val orderItemService: OrderItemService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createInvoice(@RequestBody invoice: PostInvoiceRequest){
        val order = orderService.findOrderById(invoice.order)
        val orderitem = orderItemService.findOrderItemById(invoice.orderitem)
        invoiceService.createInvoice(invoice.toInvoiceModel(order, orderitem))
    }

    @GetMapping
    fun findInvoice(): List<InvoiceModel>{
        return invoiceService.findInvoice()
    }

    @GetMapping("/{id}")
    fun findInvoiceById(@PathVariable id: Int):InvoiceModel{
        return invoiceService.findInvoiceById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateInvoice(@PathVariable id: Int, @RequestBody invoice: PutInvoiceRequest){
        val savedInvoice = invoiceService.findInvoiceById(id)
        val updatedOrder = orderService.findOrderById(invoice.order)
        val updatedOrderItem = orderItemService.findOrderItemById(invoice.orderitem)
        invoiceService.updateInvoice(invoice.toInvoiceModel(savedInvoice, updatedOrder, updatedOrderItem))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteInvoice(@PathVariable id: Int){
        invoiceService.deleteInvoice(id)
    }
}