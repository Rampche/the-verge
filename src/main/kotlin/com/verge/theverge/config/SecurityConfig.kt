package com.verge.theverge.config

import com.verge.theverge.repository.EmployeeRepository
import com.verge.theverge.security.AuthFilter
import com.verge.theverge.security.AuthenticationFilter
import com.verge.theverge.security.JwtUtil
import com.verge.theverge.services.UserDetailCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain



@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val employeeRepository: EmployeeRepository,
    private val userDetails: UserDetailCustomService,
    private val jwtUtil: JwtUtil,
    private val authenticationConfiguration: AuthenticationConfiguration
) {
    private val listOfPublicMatchers = arrayOf(
        "/customers",
        "/employees",
        "/tables",
        "/orders",
        "/reservations",
        "/purchases",
        "/login"
    )

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic(Customizer.withDefaults())
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilter(AuthenticationFilter(authenticationManager(), employeeRepository, jwtUtil))

            .csrf { csrf: CsrfConfigurer<HttpSecurity>? -> csrf?.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(HttpMethod.POST, *listOfPublicMatchers).permitAll()
                    .requestMatchers("/**").hasRole("ADMIN")
                    //.anyRequest().authenticated()
                    //.requestMatchers(HttpMethod.POST, *listOfPublicMatchers).permitAll()
            }
            .formLogin(Customizer.withDefaults())
        return http.build()
    }





}