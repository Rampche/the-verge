package com.verge.theverge.services.seed


import com.verge.theverge.enums.CustomerStatus
import com.verge.theverge.enums.TableStatus
import com.verge.theverge.models.CustomerModel
import com.verge.theverge.models.ItemsModel
import com.verge.theverge.models.TableModel
import com.verge.theverge.repository.CustomerRepository
import com.verge.theverge.repository.TableRepository
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TablesSeedService @Autowired constructor (
    private val tableRepository: TableRepository
){
    @PostConstruct
    fun seedTables() {
        val tables = listOf(

            TableModel(id = 1, number = 1, capacity = 5, status = TableStatus.EMPTY),
            TableModel(id = 2, number = 2, capacity = 4, status = TableStatus.EMPTY),
            TableModel(id = 3, number = 3, capacity = 2, status = TableStatus.EMPTY),
            TableModel(id = 4, number = 4, capacity = 6, status = TableStatus.EMPTY)

        )
        tableRepository.saveAll(tables)
    }
}