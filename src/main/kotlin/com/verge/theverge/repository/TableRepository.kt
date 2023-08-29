package com.verge.theverge.repository


import com.verge.theverge.models.TableModel
import org.springframework.data.repository.CrudRepository

interface TableRepository:CrudRepository<TableModel, Int> {
    
}