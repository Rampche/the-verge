package com.verge.theverge.controller

import com.verge.theverge.controller.requests.auth.AuthRequest
import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.BadLoginException
import com.verge.theverge.repository.EmployeeRepository
import com.verge.theverge.security.JwtUtil
import com.verge.theverge.security.UserCustomDetails
import com.verge.theverge.services.UserDetailCustomService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class SignInController(
    private val authenticationManager: AuthenticationManager,
    private val userDetails: UserDetailCustomService,
    private val employeeRepository: EmployeeRepository,
    private val jwtUtil: JwtUtil
) {

    @PostMapping("/signin")
    fun signin(@RequestBody authRequest: AuthRequest): ResponseEntity<Map<String, String>> {
        try {

            val id = employeeRepository.findByEmail(authRequest.username)?.id

            val userDetails = userDetails.loadUserByUsername(id.toString())

            val authToken = UsernamePasswordAuthenticationToken(userDetails, authRequest.password)
            val authResult = authenticationManager.authenticate(authToken)

            if (authResult.isAuthenticated) {
                val user = authResult.principal as UserCustomDetails
                val token = jwtUtil.generateToken(user.id)
                val response = mapOf("token" to "Bearer $token")
                return ResponseEntity.ok(response)
            }
        } catch (ex: Exception) {
         throw BadLoginException(Errors.VG701.message.format(), Errors.VG701.code)
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

}