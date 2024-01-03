package com.verge.theverge.config

import com.verge.theverge.enums.RoleType
import com.verge.theverge.repository.EmployeeRepository
import com.verge.theverge.security.AuthenticationFilter
import com.verge.theverge.security.AuthorizationFilter
import com.verge.theverge.security.JwtUtil
import com.verge.theverge.services.UserDetailCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@EnableMethodSecurity
@Configuration
class SecurityConfig(
    private val employeeRepository: EmployeeRepository,
    private val userDetails: UserDetailCustomService,
    private val jwtUtil: JwtUtil,
    private val authenticationConfiguration: AuthenticationConfiguration
) {

    private val LIST_OF_PUBLIC_MATCHERS = arrayOf(
        "/customers",
        "/employees",
        "/tables",
        "/orders",
        "/reservations",
        "/purchases",
        "/login",
        "/auth/signin"
    )

    private val ADMIN_MATCHERS = arrayOf(
        "/admin/**"
    )

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetails)
        authProvider.setPasswordEncoder(bCryptPasswordEncoder())

        return ProviderManager(authProvider)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { it.disable() }

        http.authorizeHttpRequests { it
            .requestMatchers(HttpMethod.POST, "/login", "/auth/signin").permitAll()
            .requestMatchers("/auth/api/**").permitAll()
            .requestMatchers(HttpMethod.GET,"/").permitAll()
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers(*LIST_OF_PUBLIC_MATCHERS).permitAll()
            .requestMatchers(*ADMIN_MATCHERS).hasAuthority(RoleType.ADMIN.description)
                .anyRequest().authenticated()
            }
        http.addFilterBefore(AuthenticationFilter(authenticationManager(), employeeRepository, jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http.addFilterBefore(AuthorizationFilter(jwtUtil, userDetails), UsernamePasswordAuthenticationFilter().javaClass)
        //http.addFilter(AuthenticationFilter(authenticationManager(), employeeRepository, jwtUtil))
        //http.addFilter(AuthorizationFilter(jwtUtil, userDetails))

        http.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        return http.build()
    }

fun configureSwagger(web:WebSecurity){
    web.ignoring().requestMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui-html", "/webjars/**", "/swagger-ui")
}

}