package com.verge.theverge.security

import com.verge.theverge.enums.EmployeeStatus
import com.verge.theverge.models.EmployeeModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(private val employeeModel: EmployeeModel): UserDetails {
    val id = employeeModel.id
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        employeeModel.role.map { SimpleGrantedAuthority(it.description) }.toMutableList()
    override fun getPassword(): String = employeeModel.password
    override fun getUsername(): String = employeeModel.id.toString()
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = employeeModel.status == EmployeeStatus.ACTIVE
}