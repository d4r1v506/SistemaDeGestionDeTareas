package ec.edu.ups.gestor_tareas.services;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {

	public String generateToken(String username);
}
