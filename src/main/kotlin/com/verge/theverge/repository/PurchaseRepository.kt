package com.verge.theverge.repository

import com.verge.theverge.controller.responses.CustomerResponse
import com.verge.theverge.enums.CustomerStatus
import com.verge.theverge.models.CustomerModel
import com.verge.theverge.models.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository:CrudRepository<PurchaseModel, Int> {

}