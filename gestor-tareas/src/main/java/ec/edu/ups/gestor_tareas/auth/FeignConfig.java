package ec.edu.ups.gestor_tareas.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {

    private String token;
	
	@Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (token != null && !token.isEmpty()) {
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }
}
