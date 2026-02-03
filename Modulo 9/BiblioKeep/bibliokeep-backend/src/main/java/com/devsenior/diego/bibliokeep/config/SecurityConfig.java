// package com.devsenior.diego.bibliokeep.config;

// import java.util.List;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) {
//         http
//             .csrf(c -> c.disable())
//                 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                 .authorizeHttpRequests(auth -> auth
//                     .anyRequest().authenticated()
//                 );
//         return http.build();
//     }

//     private CorsConfigurationSource corsConfigurationSource(){
//         var configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(List.of("http://localhost:4200"));
//         configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTION"));
//         configuration.setAllowedHeaders(List.of("Autorization","Content-Type","X-Tenant-ID","user-id"));
//         configuration.setAllowCredentials(true);

//         var source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
