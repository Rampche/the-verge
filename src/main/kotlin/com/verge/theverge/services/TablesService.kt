package com.verge.theverge.services

import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.NotFoundException
import com.verge.theverge.models.TableModel
import com.verge.theverge.repository.TableRepository
import org.springframework.stereotype.Service

@Service
class TablesService(
    private val tableRepository: TableRepository
) {


    //Create a new table
    fun createTable(table: TableModel){
        tableRepository.save(table)
    }

    //Find table by Id
    fun findTableById(id: Int): TableModel {
        if (!tableRepository.existsById(id)){
            throw NotFoundException(Errors.VG202.message.format(id), Errors.VG202.code)
        }
        return tableRepository.findById(id).orElseThrow()
    }

    //Find all tables
    fun getAllTables(): List<TableModel>{
        return tableRepository.findAll().toList()
    }

    //Update table
    fun updateTable(table: TableModel) {
        if (!tableRepository.existsById(table.id!!)){
            throw NotFoundException(Errors.VG202.message.format(table.id), Errors.VG202.code)
        }
        tableRepository.save(table)
    }

    //Delete table
    fun deleteTable(id: Int) {
        if (!tableRepository.existsById(id)){
            throw NotFoundException(Errors.VG202.message.format(id), Errors.VG202.code)
        }
        val table = findTableById(id)
        updateTable(table)

    }


}