package ec.edu.ups.gestor_tareas.servicesImpl;

import java.util.Date;

import org.springframework.stereotype.Service;

import ec.edu.ups.gestor_tareas.services.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final String secret = "mysupersecretkey";
    private final long expiration = 3600;  

	@Override
	public String generateToken(String username) {
		 return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
	                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
	                .compact();
	}

}
