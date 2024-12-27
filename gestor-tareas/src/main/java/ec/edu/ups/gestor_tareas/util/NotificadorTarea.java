package ec.edu.ups.gestor_tareas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificadorTarea {

	private static final Logger logger = LoggerFactory.getLogger(NotificadorTarea.class);

    public void notificacionTareaAsignada(String codigoTarea, String tituloTarea, String cedulaUsuario) {

        logger.info("Tarea asignada: [CODIGO: {}, TITULO: {}, Usuario: {}]", codigoTarea, tituloTarea, cedulaUsuario);
    }
}
