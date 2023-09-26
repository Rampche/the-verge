package com.verge.theverge.security

import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.BadLoginException
import com.verge.theverge.services.UserDetailCustomService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userDetails: UserDetailCustomService
):BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader("Authorization")
        if (authorization != null && authorization.startsWith("Bearer ")) {
            val auth = getAuthentication(authorization.split(" ")[1])
            SecurityContextHolder.getContext().authentication = auth

        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String):UsernamePasswordAuthenticationToken {
        if (!jwtUtil.isValidToken(token)) {
            throw BadLoginException(Errors.VG801.message.toString(), Errors.VG801.code)

        }
        val subject = jwtUtil.getSubject(token)
        val employee = userDetails.loadUserByUsername(subject)

        return UsernamePasswordAuthenticationToken(employee, null, employee.authorities)

    }

}