package ec.edu.ups.gestor_tareas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ec.edu.ups.gestor_tareas.models.AuthResponse;
import ec.edu.ups.gestor_tareas.models.LoginRequest;

@FeignClient(name = "authService", url = "${auth.service.url}")
public interface AuthClient {

	@PostMapping("/auth/getToken")
    ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest);
}
