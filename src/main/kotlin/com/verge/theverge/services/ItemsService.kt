package com.verge.theverge.services

import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.NotFoundException
import com.verge.theverge.models.ItemsModel
import com.verge.theverge.repository.ItemsRepository
import org.springframework.stereotype.Service

@Service

class ItemsService(
   private val itemsRepository: ItemsRepository
) {

    //Create a new item
    fun createItem(item: ItemsModel){
        itemsRepository.save(item)
    }

   //Show all items
    fun getAllItems(): List<ItemsModel>{
        return itemsRepository.findAll().toList()
    }

    //Find item by Id
    fun findItemById(id: Int): ItemsModel{
        if (!itemsRepository.existsById(id)){
            throw NotFoundException(Errors.VG601.message.format(id), Errors.VG601.code)
        }
        return itemsRepository.findById(id).orElseThrow { NotFoundException(Errors.VG601.message.format(id), Errors.VG601.code) }
    }

    //Update item
    fun updateItem(item: ItemsModel) {
        if (!itemsRepository.existsById(item.id!!)){
            throw NotFoundException(Errors.VG601.message.format(item.id), Errors.VG601.code)
        }
        itemsRepository.save(item)
    }


    //Delete Item
    fun deleteItem(id: Int) {
        if (!itemsRepository.existsById(id)){
            throw NotFoundException(Errors.VG601.message.format(id), Errors.VG601.code)
        }
        itemsRepository.deleteById(id)
    }

    fun findAllById(itemsIds: Set<Int>): List<ItemsModel> {
        return itemsRepository.findAllById(itemsIds).toList()
    }

}