package com.verge.theverge.controller

import com.verge.theverge.controller.requests.auth.AuthRequest
import com.verge.theverge.security.AuthenticationFilter
import com.verge.theverge.security.JwtUtil
import com.verge.theverge.services.UserDetailCustomService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailCustomService: UserDetailCustomService,
    private val jwtUtil: JwtUtil,
    private val authenticationFilter: AuthenticationFilter

){

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest):ResponseEntity<*>{
        try {
            val authenticate = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
            )
        }

    }




}



//fun login(@RequestBody authRequest: AuthRequest):ResponseEntity<*>{
//    try{
//        val authenticate = authenticationManager.authenticate(
//            UsernamePasswordAuthenticationToken(
//                authRequest.username,
//                authRequest.password
//            )
//        )
//
//    }