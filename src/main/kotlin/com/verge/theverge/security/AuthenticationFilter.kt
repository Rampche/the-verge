package com.verge.theverge.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.verge.theverge.controller.requests.auth.AuthRequest
import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.BadLoginException
import com.verge.theverge.repository.EmployeeRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    private val authenticationManager: AuthenticationManager,
    private val employeeRepository: EmployeeRepository,
    private val jwtUtil: JwtUtil
    ) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val authRequest = jacksonObjectMapper().readValue(request.inputStream, AuthRequest::class.java)
            val id = employeeRepository.findByEmail(authRequest.username)?.id
            val authToken = UsernamePasswordAuthenticationToken(id, authRequest.password)
            return authenticationManager.authenticate(authToken)
        } catch (ex: Exception){
            throw BadLoginException(Errors.VG701.message.format(), Errors.VG701.code)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val id = (authResult.principal as UserCustomDetails).id
        val token = jwtUtil.generateToken(id)
        response.addHeader("Authorization", "Bearer $token")
    }

}