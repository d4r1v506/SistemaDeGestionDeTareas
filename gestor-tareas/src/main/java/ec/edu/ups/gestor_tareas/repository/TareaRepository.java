package ec.edu.ups.gestor_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.ups.gestor_tareas.models.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{

}