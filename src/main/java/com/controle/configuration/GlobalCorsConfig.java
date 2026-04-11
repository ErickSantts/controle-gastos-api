package com.controle.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permite a origem do Angular
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));

        // Permite TODOS os métodos (GET, POST, PUT, DELETE, PATCH, OPTIONS)
        config.setAllowedMethods(Arrays.asList("*"));

        // Permite TODOS os headers (importante para Content-Type e Authorization)
        config.setAllowedHeaders(Arrays.asList("*"));

        // Permite enviar cookies/autenticação se necessário
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
