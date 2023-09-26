package com.verge.theverge.controller

import com.verge.theverge.controller.requests.customer.PostCustomerRequest
import com.verge.theverge.controller.requests.customer.PutCustomerRequest
import com.verge.theverge.controller.responses.CustomerResponse
import com.verge.theverge.extensions.toCustomerModel
import com.verge.theverge.extensions.toResponse
import com.verge.theverge.services.CustomerService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.awt.print.Pageable

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService,
    ) {

    @Operation(summary = "Create a customer", method = "GET")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody @Valid customer: PostCustomerRequest){
        customerService.createCustomer(customer.toCustomerModel())
    }
    @Operation(summary = "Find all customers, and search by name", method = "GET")
    @GetMapping
    fun getAllCustomers(@RequestParam name:String?): List<CustomerResponse> {
        return customerService.getAllCustomers(name).map {it.toResponse()}
    }
    @Operation(summary = "Obtain all active customers", method = "GET")
    @GetMapping("/active")
    fun findActives():List<CustomerResponse>{
        return customerService.findActives().map { it.toResponse() }
    }
    @Operation(summary = "Find customer by ID", method = "GET")
    @GetMapping("/{id}")
    fun findCustomerById(@PathVariable id:Int):CustomerResponse{
        return customerService.findCustomerById(id).toResponse()
    }

    @Operation(summary = "Edit a customer", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest){
        val customerSaved = customerService.findCustomerById(id)
        customerService.updateCustomer(customer.toCustomerModel(customerSaved))
    }

    @Operation(summary = "Delete a customer", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int){
        customerService.deleteCustomer(id)
    }
}