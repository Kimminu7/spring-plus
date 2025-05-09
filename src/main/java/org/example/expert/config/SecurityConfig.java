package org.example.expert.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter JwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF, form 로그인, http 인증 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                // JWT 사용을 위한 세션 STATELESS로 설정 ( 세션 정보 저장 X)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 인증 인가 URL 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/auth/**").permitAll()  // 화이트 리스트
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN 권한 있어야 사용 가능
                        .anyRequest().authenticated()) // 나머지는 인증 필요
                .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
