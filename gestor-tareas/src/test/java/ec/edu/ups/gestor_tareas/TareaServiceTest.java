package ec.edu.ups.gestor_tareas;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ec.edu.ups.gestor_tareas.models.Estado;
import ec.edu.ups.gestor_tareas.models.Tarea;
import ec.edu.ups.gestor_tareas.repository.EstadoRepository;
import ec.edu.ups.gestor_tareas.repository.TareaRepository;
import ec.edu.ups.gestor_tareas.services.TareaService;

class TareaServiceTest {

	@Mock
	private EstadoRepository estadoRepository;
	
	@Mock
	private TareaRepository tareaRepository;
	
	@InjectMocks
	private TareaService tareaService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCrearTeareConEstadoValido() {
		String estadoNombre = "BACKLOG";
		Estado estadoMock = new Estado();
		estadoMock.setNombre(estadoNombre);
		
		Tarea tareaMock = new Tarea();
		Estado estado = new Estado();
		//estado.setId(1L);
		estado.setNombre(estadoNombre);
		tareaMock.setEstado(estado);		
		
		Tarea tareaGuardada = new Tarea();
		tareaGuardada.setId(1L);
		tareaGuardada.setTitulo("Tarea de test");
		tareaGuardada.setEstado(estadoMock);
		
		when(estadoRepository.findByNombre(estadoNombre)).thenReturn(Optional.of(estadoMock));
		//when(estadoRepository.findByNombre(estadoNombre)).thenReturn(Optional.empty());
	    when(tareaRepository.save(tareaMock)).thenReturn(tareaGuardada);
	    
	    Tarea resultado = tareaService.crearTarea(tareaMock, estadoNombre);
	    
	    assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Tarea de test", resultado.getTitulo());
        assertEquals(estadoNombre, resultado.getEstado().getNombre());

        verify(estadoRepository, times(1)).findByNombre(estadoNombre);
        verify(tareaRepository, times(1)).save(tareaMock);
	}
	
	@Test
	void testCrearTareaConEstadoInvalido() {
	    String estadoNombre = "INVALIDO";
	    Tarea tareaMock = new Tarea();

	    when(estadoRepository.findByNombre(estadoNombre)).thenReturn(Optional.empty());

	    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	        tareaService.crearTarea(tareaMock, estadoNombre);
	    });

	    assertEquals("Estado invalido: " + estadoNombre, exception.getMessage());

	    verify(estadoRepository, times(1)).findByNombre(estadoNombre);
	    verify(tareaRepository, times(0)).save(tareaMock);
	}
}
