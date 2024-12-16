package ec.edu.ups.gestor_tareas.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityProperties {

	@Value("${app.security.username}")
	private String username;

	@Value("${app.security.password}")
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
