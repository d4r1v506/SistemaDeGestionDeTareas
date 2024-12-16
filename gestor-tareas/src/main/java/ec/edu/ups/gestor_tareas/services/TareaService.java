package ec.edu.ups.gestor_tareas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.ups.gestor_tareas.models.Estado;
import ec.edu.ups.gestor_tareas.models.Tarea;
import ec.edu.ups.gestor_tareas.repository.EstadoRepository;
import ec.edu.ups.gestor_tareas.repository.TareaRepository;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	private final UsuarioService usuarioService;
	
	 @Autowired
	    public TareaService(TareaRepository tareaRepository, UsuarioService usuarioService) {
	        this.tareaRepository = tareaRepository;
	        this.usuarioService = usuarioService;
	    }

	public Tarea obtenerTareaPorId(Long id) {
		return tareaRepository.findById(id).orElse(null);

	}

	public Tarea crearTarea(Tarea tarea, String estadoNombre) {
		Estado estado = estadoRepository.findByNombre(estadoNombre)
				.orElseThrow(() -> new IllegalArgumentException("Estado invalido: " + estadoNombre));
		tarea.setEstado(estado);
		return tareaRepository.save(tarea);
	}

	public Tarea actualizarEstadoTarea(Long id, String estado) {

		Optional<Tarea> tareaOpcional = tareaRepository.findById(id);

		if (tareaOpcional.isPresent()) {
			Tarea tarea = tareaOpcional.get();
			try {
				Estado estadoEncontrado = estadoRepository.findByNombre(estado)
						.orElseThrow(() -> new IllegalArgumentException("Estado invalido: " + estado));
				tarea.setEstado(estadoEncontrado);				
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Estado inválido: " + estado);
			}
			return tareaRepository.save(tarea);
		}
		return null;
	}

	public boolean borrarTarea(Long id) {
		Optional<Tarea> tareaOpcional = tareaRepository.findById(id);
		if (tareaOpcional.isPresent()) {
			tareaRepository.delete(tareaOpcional.get());
			return true;
		}
		return false;
	}

	public void asignarTareaAUsuario(Long idTarea, String idUsuario) {
		
		 // Verificar si el usuario existe antes de asignar la tarea
      /*  if (!usuarioService.verificarUsuario(idUsuario)) {
            throw new RuntimeException("El usuario no existe.");
        }*/
		 // Buscar la tarea por su ID
        Tarea tarea = tareaRepository.findById(idTarea)
                .orElseThrow(() -> new RuntimeException("La tarea no existe."));

        // Asignar el ID del usuario
        tarea.setIdUsuario(idUsuario);

        // Guardar los cambios
        tareaRepository.save(tarea);
		
	}

}
