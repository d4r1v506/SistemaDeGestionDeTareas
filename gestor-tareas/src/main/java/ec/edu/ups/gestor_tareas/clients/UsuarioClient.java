package ec.edu.ups.gestor_tareas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import ec.edu.ups.gestor_tareas.auth.FeignConfig;
import ec.edu.ups.gestor_tareas.util.RespuestaGenericaServicio;

@FeignClient(name = "usuario-service", url = "${usuario.service.url}", configuration = FeignConfig.class )
public interface UsuarioClient {
	
	@GetMapping("/usuarios/{idUsuario}")
	RespuestaGenericaServicio usuarioExiste(@PathVariable String idUsuario, @RequestHeader("Authorization") String authorizationHeader);

}
