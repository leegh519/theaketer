package com.chbb.theaketing.core.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AccessHandler accessHandler;

    private final EntryPoint entryPoint;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .securityContext((securityContext) -> {
                    securityContext.securityContextRepository(delegatingSecurityContextRepository());
                    securityContext.requireExplicitSave(true);
                })
                .cors(t -> t.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/**/login"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/**/signup"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/**/api-docs/**"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/*/*/auth/**"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/*/*/auth/**"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/*/*/drama/**"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/error"))
                        .permitAll()
                        // .anyRequest()
                        // .permitAll())
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(
                        exceptionHandlingCustomizer -> exceptionHandlingCustomizer
                                .accessDeniedHandler(accessHandler)
                                .authenticationEntryPoint(entryPoint))
                .logout(logoutCustomizer -> logoutCustomizer.logoutUrl("/logout")
                        .permitAll()
                        .deleteCookies("JSESSIONID", "remember-me")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(200);
                        }));

        return http.build();
    }

    /**
     * 이 메서드는 정적 자원에 대해 보안을 적용하지 않도록 설정한다.
     * 정적 자원은 보통 HTML, CSS, JavaScript, 이미지 파일 등을 의미하며, 이들에 대해 보안을 적용하지 않음으로써 성능을
     * 향상시킬 수 있다.
     */
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    DelegatingSecurityContextRepository delegatingSecurityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository());
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://theaketing.kro.kr", "https://theaketing.kro.kr"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
