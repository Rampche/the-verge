package com.verge.theverge.security

import com.verge.theverge.exception.JwtExcpetion
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null


    fun generateToken(id: Int): String {
        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(Date(System.currentTimeMillis() + (expiration!! * 1000)))
            .signWith(Keys.hmacShaKeyFor(secret!!.toByteArray()))
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        if (claims.subject == null || claims.expiration == null || Date().after((claims.expiration))){
            return false
        }
        return true
    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts
                .parserBuilder()
                .setSigningKey(secret!!.toByteArray())
                .build()
                .parseClaimsJws(token)
                .body
        } catch (ex: JwtException) {
            throw JwtExcpetion("Invalid token", "000")
        }
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }

}