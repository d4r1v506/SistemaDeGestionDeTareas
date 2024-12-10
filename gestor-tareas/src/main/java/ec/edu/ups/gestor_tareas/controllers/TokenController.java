package ec.edu.ups.gestor_tareas.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.gestor_tareas.models.AuthResponse;
import ec.edu.ups.gestor_tareas.models.LoginRequest;
import ec.edu.ups.gestor_tareas.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class TokenController {
	
	 	@Value("${app.security.username}")
	    private String appUsername;

	    @Value("${app.security.password}")
	    private String appPassword;
		   
    private final AuthService authService;
    
    public TokenController(AuthService authService) {
    	this.authService = authService;
    }

    @PostMapping("/getToken")
    public ResponseEntity<?> getToken(@RequestBody LoginRequest loginRequest) {
    	 if (appUsername.equals(loginRequest.getUsername()) && appPassword.equals(loginRequest.getPassword())) {
             String token = authService.generateToken(loginRequest.getUsername());
             return ResponseEntity.ok().body(new AuthResponse(token, "Bearer"));
         } else {
             return ResponseEntity.status(401).body("Invalid username or password");
         }
    }


}
