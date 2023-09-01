package com.verge.theverge.services.seed


import com.verge.theverge.enums.CustomerStatus
import com.verge.theverge.enums.EmployeeStatus
import com.verge.theverge.enums.RoleType
import com.verge.theverge.models.CustomerModel
import com.verge.theverge.models.EmployeeModel
import com.verge.theverge.models.ItemsModel
import com.verge.theverge.repository.CustomerRepository
import com.verge.theverge.repository.EmployeeRepository
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class EmployeeSeedService @Autowired constructor (
    private val employeeRepository: EmployeeRepository
){
    @PostConstruct
    fun seedEmployees() {
        val employees = listOf(

            EmployeeModel(id = 1, name = "Daniel Rodrigues", email = "daniel.rodrigues@gmail.com", role = RoleType.COMMON, password = "7895421qw", status = EmployeeStatus.ACTIVE),
            EmployeeModel(id = 2, name = "Gabriel Urben", email = "gabriel.urben@gmail.com", role = RoleType.ADMIN,password = "zxxvb453546", status = EmployeeStatus.ACTIVE),
            EmployeeModel(id = 3, name = "Nuno Jesus", email = "jose.nunes@gmail.com", role = RoleType.ADMIN, password = "8ss565215", status = EmployeeStatus.DISMISSED),

        )
        employeeRepository.saveAll(employees)
    }
}