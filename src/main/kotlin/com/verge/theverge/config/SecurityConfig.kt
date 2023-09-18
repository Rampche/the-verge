package com.verge.theverge.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Suppress("DEPRECATION")
@EnableWebSecurity
@Configuration
class SecurityConfig() {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authz -> authz
            authz.anyRequest().permitAll()
            }
            .httpBasic(Customizer.withDefaults())
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .formLogin(Customizer.withDefaults())
            .cors{ cors: CorsConfigurer<HttpSecurity>? -> cors?.disable() }
        //(Customizer.withDefaults())
            .csrf(Customizer.withDefaults())

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager().apply {
            createUser(
                User.withUsername("user")
                    .password(bCryptPasswordEncoder().encode("password"))
                    .roles("USER")
                    .build()
            )
        }
    }
}
























//
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http.authorizeHttpRequests { authz -> authz
//            //.requestMatchers(HttpMethod.POST).permitAll()
//            authz.anyRequest().authenticated()
//            }
//            .httpBasic(Customizer.withDefaults())
//            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
//                session
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            }
//            .formLogin(Customizer.withDefaults())
//            .cors(Customizer.withDefaults())
//            .csrf(Customizer.withDefaults())
//
//        return http.build()
//    }

//    @Bean
//    fun springSecurityFilterChainCors(http: ServerHttpSecurity): SecurityWebFilterChain? {
//        http
//            .cors { cors: CorsSpec -> cors.disable() }
//        return http.build()
//    }
//
//    @Bean
//    @Throws(Exception::class)
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
//        http
//            .csrf(Customizer.withDefaults())
//        return http.build()
//    }

// }