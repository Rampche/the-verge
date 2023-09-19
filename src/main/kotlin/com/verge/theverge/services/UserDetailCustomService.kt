package com.verge.theverge.services

import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.BadLoginException
import com.verge.theverge.repository.EmployeeRepository
import com.verge.theverge.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailCustomService(
    private val employeeRepository: EmployeeRepository
):UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val employee = employeeRepository.findById(id.toInt())
            .orElseThrow { BadLoginException(Errors.VG701.message.format(), Errors.VG701.code) }
        return UserCustomDetails(employee)
    }
}