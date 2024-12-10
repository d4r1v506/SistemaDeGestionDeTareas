package ec.edu.ups.gestor_tareas.auth;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	private final String secret = "mysupersecretkey";

    public boolean validateToken(String token) {
    	 try {
    	        Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
    	        return true;
    	    } catch (SignatureException e) {
    	        System.out.println("Firma inválida del token JWT: " + e.getMessage());
    	    } catch (ExpiredJwtException e) {
    	        System.out.println("Token JWT expirado: " + e.getMessage());
    	    } catch (UnsupportedJwtException e) {
    	        System.out.println("Token JWT no soportado: " + e.getMessage());
    	    } catch (MalformedJwtException e) {
    	        System.out.println("Token JWT malformado: " + e.getMessage());
    	    } catch (IllegalArgumentException e) {
    	        System.out.println("El token JWT es nulo o vacío: " + e.getMessage());
    	    }
    	    return false; 
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                   .setSigningKey(secret.getBytes())
                   .parseClaimsJws(token)
                   .getBody();
    }

    public Authentication getAuthentication(String token) {
    	 Claims claims = getClaims(token);
    	    if (claims == null) {
    	        return null;
    	    }

    	    String username = claims.getSubject();
    	    if (username == null) {
    	        return null;
    	    }
    	    return new UsernamePasswordAuthenticationToken(username, null, null);
    }
}
