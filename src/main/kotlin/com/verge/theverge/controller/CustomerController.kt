package com.verge.theverge.controller

import com.verge.theverge.controller.requests.customer.PostCustomerRequest
import com.verge.theverge.controller.requests.customer.PutCustomerRequest
import com.verge.theverge.extensions.toCustomerModel
import com.verge.theverge.models.CustomerModel
import com.verge.theverge.services.CustomerService
import com.verge.theverge.services.ReservationService
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

//Here, in the actions, is where all the functions will be called from the CustomerService class.

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService,
    val reservationService: ReservationService
    ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest){
        customerService.createCustomer(customer.toCustomerModel())
    }

    @GetMapping
    fun getAllCustomers(@RequestParam name:String?): List<CustomerModel> {
        return customerService.getAllCustomers(name)
    }

    @GetMapping("/active")
    fun findActives():List<CustomerModel>{
        return customerService.findActives()
    }

    @GetMapping("/{id}")
    fun findCustomerById(@PathVariable id:Int):CustomerModel{
        return customerService.findCustomerById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest){
        val customerSaved = customerService.findCustomerById(id)
        customerService.updateCustomer(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Int){
        customerService.deleteCustomer(id)
    }
}