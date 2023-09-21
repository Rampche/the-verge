package com.verge.theverge.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer.JwtConfigurer
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null


    fun generateToken(id: Int):String{
     return JWT
         .create()
         .withSubject(id.toString())
         .withExpiresAt(Date(System.currentTimeMillis() + expiration!!))
         .sign(Algorithm.HMAC512(secret!!.toByteArray()))
    }

}