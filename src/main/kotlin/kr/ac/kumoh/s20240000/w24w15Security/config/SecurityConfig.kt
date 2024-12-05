package kr.ac.kumoh.s20240000.w24w15Security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig {

    // CORS 설정을 위한 CorsConfigurationSource 정의
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.apply {
            allowedOrigins = listOf("http://localhost:3000") // 허용할 도메인
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
            allowedHeaders = listOf("*") // 허용할 헤더
            allowCredentials = true // 자격 증명(쿠키 등)을 허용할지 여부
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration) // 모든 경로에 적용
        return source
    }

    // SecurityFilterChain을 통한 CORS 설정
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors { it.configurationSource(corsConfigurationSource()) } // CORS 설정 적용
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/api/auth/**").permitAll() // 로그인, 회원가입 경로는 허용
                    .anyRequest().authenticated()
            }
            .csrf { csrf -> csrf.disable() }
            .build()
    }
}