package com.verge.theverge.repository

import com.verge.theverge.enums.EmployeeStatus
import com.verge.theverge.enums.RoleType
import com.verge.theverge.models.EmployeeModel
import org.springframework.data.repository.CrudRepository

interface EmployeeRepository:CrudRepository<EmployeeModel, Int> {
    fun findByNameContaining(name: String): List<EmployeeModel>
    fun findByRole(type: RoleType): List<EmployeeModel>
    fun findByStatus(status: EmployeeStatus): List<EmployeeModel>
    fun existsByStatus(status: EmployeeStatus):Boolean
}