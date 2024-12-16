package ec.edu.ups.gestor_tareas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ec.edu.ups.gestor_tareas.clients.AuthClient;
import ec.edu.ups.gestor_tareas.clients.UsuarioClient;
import ec.edu.ups.gestor_tareas.models.AuthResponse;
import ec.edu.ups.gestor_tareas.models.LoginRequest;
import ec.edu.ups.gestor_tareas.util.RespuestaGenericaServicio;

@Service
public class UsuarioService {

	    private final AuthClient authClient;
	    private final UsuarioClient usuarioClient;

	    @Autowired
	    public UsuarioService(AuthClient authClient, UsuarioClient usuarioClient) {
	        this.authClient = authClient;
	        this.usuarioClient = usuarioClient;
	    }

	   /* public boolean verificarUsuario(String idUsuario) {
	    	 // Primero obtenemos el token de la API de autenticación
	        ResponseEntity<AuthResponse> tokenResponseEntity = authClient.login(new LoginRequest("admin", "admin123"));
	        AuthResponse tokenResponse = tokenResponseEntity.getBody(); // Obtener el cuerpo de la respuesta
	        // Asegúrate de que el cuerpo no sea null
	        if (tokenResponse == null) {
	            throw new RuntimeException("No se pudo obtener el token de autenticación.");
	        }
	        String token = tokenResponse.getToken();
	        // Ahora, realiza la llamada a la API de usuarios con el token
	        RespuestaGenericaServicio usuarioExiste = usuarioClient.usuarioExiste(idUsuario, token);

	        return usuarioExiste;
	    }	*/
}