package ec.edu.ups.gestor_tareas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.gestor_tareas.models.Estado;
import ec.edu.ups.gestor_tareas.models.Tarea;
import ec.edu.ups.gestor_tareas.models.TareaDTO;
import ec.edu.ups.gestor_tareas.services.TareaService;
import ec.edu.ups.gestor_tareas.util.RespuestaGenericaServicio;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/tareas")
public class TareaController {

	@Autowired
	private TareaService tareaService;

	@GetMapping("/test/{nombre}")
	public ResponseEntity<?> test(@PathVariable("nombre") String nombre) {
		return ResponseEntity.status(HttpStatus.OK).body(nombre);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> ObtenerTareaPorId(@PathVariable Long id) {
		try {
			Tarea tarea = tareaService.obtenerTareaPorId(id);

			if (tarea == null) {
				RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("ERROR", null,
						new String[] { "Tarea no encontrada con ID: " + id });
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
			}
			RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("SUCCESS", tarea,
					new String[] { "Tarea obtenida exitosamente" });
			return ResponseEntity.ok(respuesta);

		} catch (Exception e) {
			RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("ERROR", null,
					new String[] { "Error interno del servidor: " + e.getMessage() });
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> crearTarea(@RequestBody TareaDTO tareaDTO) {
		try {
			Tarea tarea = new Tarea();
			tarea.setCodigoTarea(tareaDTO.getCodigoTarea());
			tarea.setTitulo(tareaDTO.getTitulo());
			tarea.setDescripcion(tareaDTO.getDescripcion());
			tarea.setCriteriosAceptacion(tareaDTO.getCriteriosAceptacion());
			tarea.setFechaInicio(tareaDTO.getFechaInicio());
			tarea.setFechaFinalizacion(tareaDTO.getFechaFinalizacion());
			tarea.setTiempoDesarrollo(tareaDTO.getTiempoDesarrollo());
			Tarea tareaCreada = tareaService.crearTarea(tarea, tareaDTO.getEstado());
			RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("SUCCESS", tareaCreada,
					new String[] { "Tarea creada exitosamente" });
			return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		} catch (IllegalArgumentException e) {
			RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("ERROR", null,
					new String[] { e.getMessage() });
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarEstadoTarea(@PathVariable Long id, @RequestBody Estado estadoRequest) {

		try {
			String estado = estadoRequest.getNombre();
			Tarea tareaActualizada = tareaService.actualizarEstadoTarea(id, estado);
			if (tareaActualizada == null) {
				RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("ERROR", null,
						new String[] { "Tarea no encontrada con ID: " + id });
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
			}
			RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("SUCCESS", tareaActualizada,
					new String[] { "Tarea actualizada exitosamente" });
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("ERROR", null,
					new String[] { "Error interno del servidor: " + e.getMessage() });
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarTarea(@PathVariable Long id) {
		try {
		boolean isBorrada = tareaService.borrarTarea(id);
		if (!isBorrada) {
			RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("ERROR", null,
					new String[] { "Tarea no encontrada con ID: " + id });
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
		}
		RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("SUCCESS", null,
				new String[] { "Tarea con ID: "+id+" eliminada exitosamente" });
		return ResponseEntity.ok(respuesta);
		}catch (Exception e) {
			RespuestaGenericaServicio respuesta = new RespuestaGenericaServicio("ERROR", null,
					new String[] { "Error interno del servidor: " + e.getMessage() });
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
		
	}
}
