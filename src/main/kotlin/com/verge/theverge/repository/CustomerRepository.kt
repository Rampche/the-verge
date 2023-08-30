package com.verge.theverge.repository

import com.verge.theverge.controller.responses.CustomerResponse
import com.verge.theverge.enums.CustomerStatus
import com.verge.theverge.models.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository:CrudRepository<CustomerModel, Int> {
    fun findByNameContaining(name: String): List<CustomerModel>
    fun findByStatus(status: CustomerStatus): List<CustomerModel>
    fun existsByStatus(status: CustomerStatus):Boolean
}