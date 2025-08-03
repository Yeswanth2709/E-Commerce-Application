package org.example.productservice.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Applies to all endpoints
                        .allowedOrigins("http://localhost:4200") // Your frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Methods you want to allow
                        .allowedHeaders("*"); // Allows all headers (customize for more security)
            }
        };
    }
}

