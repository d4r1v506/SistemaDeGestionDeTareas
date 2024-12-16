package ec.edu.ups.gestor_tareas.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {

	@Value("${auth.token}")
    private String token; // Suponiendo que el token ya está almacenado aquí
	
	@Bean
    public RequestInterceptor requestInterceptor() {
		System.out.println("token es: "+token);
        return requestTemplate -> {
            if (token != null && !token.isEmpty()) {
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }
}
