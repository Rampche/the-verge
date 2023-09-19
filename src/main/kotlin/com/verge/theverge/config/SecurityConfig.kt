package com.verge.theverge.config

import com.verge.theverge.repository.EmployeeRepository
import com.verge.theverge.security.AuthenticationFilter
import com.verge.theverge.services.UserDetailCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
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
    private val userDetails: UserDetailCustomService
) {
    private val listOfPublicMatchers = arrayOf(
        "/customers",
        "/employees",
        "/tables",
        "/orders",
        "/reservations",
        "/purchases"
    )

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth -> auth
            auth.anyRequest().authenticated()
                .requestMatchers(HttpMethod.POST, *listOfPublicMatchers).permitAll()
            }
            .httpBasic(Customizer.withDefaults())
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilter(AuthenticationFilter(authenticationManager(), employeeRepository))
            .csrf{ csrf: CsrfConfigurer<HttpSecurity>? -> csrf?.disable() }
            .cors{ cors: CorsConfigurer<HttpSecurity>? -> cors?.disable() }
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder){
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }




//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        return InMemoryUserDetailsManager().apply {
//            createUser(
//                User.withUsername("user")
//                    .password(bCryptPasswordEncoder().encode("password"))
//                    .roles("USER")
//                    .build()
//            )
//        }
//    }

//    @Bean
//    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
//        return authenticationConfiguration.authenticationManager
//    }


}


//    @Bean
//    fun springSecurityFilterChainCors(http: HttpSecurity): SecurityWebFilterChain? {
//        http
//            .cors { cors: CorsConfigurer<HttpSecurity>? -> cors?.disable() }
//        return http.build()
//    }
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


//
//    @Bean
//    @Throws(Exception::class)
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
//        http
//            .csrf(Customizer.withDefaults())
//        return http.build()
//    }

// }