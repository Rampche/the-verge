package com.verge.theverge.repository


import com.verge.theverge.models.ItemsModel
import org.springframework.data.repository.CrudRepository

interface ItemsRepository:CrudRepository<ItemsModel, Int> {

}