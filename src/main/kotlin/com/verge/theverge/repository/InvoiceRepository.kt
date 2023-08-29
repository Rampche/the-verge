package com.verge.theverge.repository


import com.verge.theverge.models.InvoiceModel
import org.springframework.data.repository.CrudRepository

interface InvoiceRepository:CrudRepository<InvoiceModel, Int> {
    
}