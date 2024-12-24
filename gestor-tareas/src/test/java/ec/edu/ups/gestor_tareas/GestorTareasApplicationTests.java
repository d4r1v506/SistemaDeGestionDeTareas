package ec.edu.ups.gestor_tareas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ec.edu.ups.gestor_tareas.models.Estado;
import ec.edu.ups.gestor_tareas.models.Tarea;
import ec.edu.ups.gestor_tareas.repository.EstadoRepository;
import ec.edu.ups.gestor_tareas.repository.TareaRepository;
import ec.edu.ups.gestor_tareas.services.TareaService;

@SpringBootTest
class GestorTareasApplicationTests {

	@Autowired
	private TareaService tareaService;

	@Autowired
	private TareaRepository tareaRepository;

	private Estado estadoMock;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	void setUp() {
	        estadoMock = new Estado();
	        estadoMock.setNombre("BACKLOG");
	}

	@Test
	@Transactional
	void testCrearTareaConEstadoValido() {
		Tarea tarea = new Tarea();		
		tarea.setTitulo("Tarea de prueba");
		tarea.setEstado(estadoMock);

		Tarea tareaGuardada = tareaService.crearTarea(tarea, estadoMock.getNombre());

		assertNotNull(tareaGuardada);
		assertEquals("Tarea de prueba", tareaGuardada.getTitulo());
		assertEquals(estadoMock.getNombre(), tareaGuardada.getEstado().getNombre());

		assertTrue(tareaRepository.findById(tareaGuardada.getId()).isPresent());
	}

	@Test
	void testCrearTareaConEstadoInvalido() {		
		String estadoInvalido = "INVALIDO";
		Tarea tarea = new Tarea();
		tarea.setTitulo("Tarea inválida");

		assertThrows(IllegalArgumentException.class, () -> {
			tareaService.crearTarea(tarea, estadoInvalido);
		});
	}
}
