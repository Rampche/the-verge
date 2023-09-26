package com.verge.theverge.controller

import com.verge.theverge.controller.requests.tables.PostTableRequest
import com.verge.theverge.controller.requests.tables.PutTableRequest
import com.verge.theverge.extensions.toTableModel
import com.verge.theverge.models.TableModel
import com.verge.theverge.services.TablesService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tables")
class TableController (val tablesService: TablesService){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTable(@RequestBody table: PostTableRequest){

        tablesService.createTable(table.toTableModel())
    }

    @GetMapping
    fun getAllTables(): List<TableModel> {
        return tablesService.getAllTables()
    }

    @GetMapping("/{id}")
    fun findTableById(@PathVariable id:Int): TableModel {
        return tablesService.findTableById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateTable(@PathVariable id: Int, @RequestBody table: PutTableRequest){
        val tableSaved = tablesService.findTableById(id)
        tablesService.updateTable(table.toTableModel(tableSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTable(@PathVariable id: Int){
        tablesService.deleteTable(id)
    }

}