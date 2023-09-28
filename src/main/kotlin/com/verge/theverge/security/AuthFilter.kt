package com.verge.theverge.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.verge.theverge.controller.requests.auth.AuthRequest
import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.BadLoginException
import com.verge.theverge.repository.EmployeeRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthFilter(
    authManager: AuthenticationManager,
    private val employeeRepository: EmployeeRepository,
    private val jwtUtil: JwtUtil
):UsernamePasswordAuthenticationFilter(authManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, AuthRequest::class.java)
            val id = employeeRepository.findByEmail(loginRequest.username)?.id
            val authToken = UsernamePasswordAuthenticationToken(id, loginRequest.password)
            val authManager = authenticationManager.authenticate(authToken)
            return authManager

        } catch (ex: Exception) {
            throw BadLoginException(Errors.VG701.message.format(), Errors.VG701.code)
        }
    }



}