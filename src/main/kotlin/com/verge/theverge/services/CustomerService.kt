package com.verge.theverge.services

import com.verge.theverge.enums.CustomerStatus
import com.verge.theverge.enums.EmployeeStatus
import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.NotFoundException
import com.verge.theverge.models.CustomerModel
import com.verge.theverge.repository.CustomerRepository
import com.verge.theverge.repository.ReservationRepository
import org.springframework.stereotype.Service

@Service

class CustomerService(
    val customerRepository: CustomerRepository,
    val reservationRepository: ReservationRepository
) {

    //Create a new customer
    fun createCustomer(customer: CustomerModel){
        customerRepository.save(customer)
    }

    //Find all Actives
    fun findActives(): List<CustomerModel> {
        val activeCustomer = EmployeeStatus.ACTIVE
        if (!customerRepository.existsByStatus(CustomerStatus.ACTIVE)){
            throw NotFoundException(Errors.VG0008.message.format(activeCustomer.toString()), Errors.VG0008.code)
        }
        return customerRepository.findByStatus(CustomerStatus.ACTIVE)
    }

    //Show all customers
    fun getAllCustomers(name: String?): List<CustomerModel>{
        name?.let{
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    //Find customer by Id
    fun findCustomerById(id: Int): CustomerModel{
        if (!customerRepository.existsById(id)){
            throw NotFoundException(Errors.VG0002.message.format(id), Errors.VG0002.code)
        }
        return customerRepository.findById(id).orElseThrow { NotFoundException(Errors.VG0002.message.format(id), Errors.VG0002.code) }
    }

    //Update customer
    fun updateCustomer(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)){
            throw NotFoundException(Errors.VG0002.message.format(customer.id), Errors.VG0002.code)
        }
        customerRepository.save(customer)
    }

    //Delete Customer
    fun deleteCustomer(id: Int) {
        if (!customerRepository.existsById(id)){
            throw NotFoundException(Errors.VG0002.message.format(id), Errors.VG0002.code)
        }
        val customer = findCustomerById(id)
        customer.status = CustomerStatus.DELETED
        updateCustomer(customer)

    }
}