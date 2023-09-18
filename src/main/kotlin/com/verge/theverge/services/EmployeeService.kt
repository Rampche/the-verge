package com.verge.theverge.services

import com.verge.theverge.enums.EmployeeStatus
import com.verge.theverge.enums.Errors
import com.verge.theverge.enums.RoleType
import com.verge.theverge.exception.NotFoundException
import com.verge.theverge.models.EmployeeModel
import com.verge.theverge.repository.EmployeeRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val bCrypt: BCryptPasswordEncoder

) {

    //Create a new Employee
    fun createEmployee(employee: EmployeeModel) {
        val employeeCopy = employee.copy(
            role = setOf(RoleType.COMMON),
            password = bCrypt.encode((employee.password))
        )
        employeeRepository.save(employeeCopy)
    }

    //Show all Employees
    fun getAllEmployees(name: String?): List<EmployeeModel> {
        name?.let { return employeeRepository.findByNameContaining(it) }
        return employeeRepository.findAll().toList()
    }

    //Find all Actives
    fun findActives(): List<EmployeeModel> {
        val activeEmployee = EmployeeStatus.ACTIVE
        if (!employeeRepository.existsByStatus(EmployeeStatus.ACTIVE)){
            throw NotFoundException(Errors.VG302.message.format(activeEmployee.toString()), Errors.VG302.code)
        }
        return employeeRepository.findByStatus(EmployeeStatus.ACTIVE)
    }

    //Find all dismisseds employees
    fun findDismisseds(): List<EmployeeModel> {
        val dismissedEmployee = EmployeeStatus.DISMISSED
        if (!employeeRepository.existsByStatus(EmployeeStatus.DISMISSED)){
            throw NotFoundException(Errors.VG302.message.format(dismissedEmployee.toString()), Errors.VG302.code)
        }
        return employeeRepository.findByStatus(EmployeeStatus.DISMISSED)
    }

    //Find by Role
    fun findByRole(type: RoleType): List<EmployeeModel> {
        return employeeRepository.findByRole(type)
    }

    //Find Employee by Id
    fun findEmployeeById(id: Int): EmployeeModel {
        if (!employeeRepository.existsById(id)){
            throw NotFoundException(Errors.VG301.message.format(id), Errors.VG301.code)
        }
        return employeeRepository.findById(id).orElseThrow()
    }

    //Update Employee
    fun updateEmployee(employee: EmployeeModel) {
        if (!employeeRepository.existsById(employee.id!!)){
            throw NotFoundException(Errors.VG301.message.format(employee.id), Errors.VG301.code)
        }
        employeeRepository.save(employee)
    }

    //Delete Employee
    fun deleteEmployee(id: Int) {
        if (!employeeRepository.existsById(id)){
            throw NotFoundException(Errors.VG301.message.format(id), Errors.VG301.code)
        }
        val employee = findEmployeeById(id)
        employee.status = EmployeeStatus.DISMISSED
        updateEmployee(employee)
    }

}