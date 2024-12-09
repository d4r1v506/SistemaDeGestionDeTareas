package ec.edu.ups.gestor_tareas.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarea {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoTarea;
    private String titulo;
    private String descripcion;
    private String criteriosAceptacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private int tiempoDesarrollo;
    private String estado;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoTarea() {
		return codigoTarea;
	}
	public void setCodigoTarea(String codigoTarea) {
		this.codigoTarea = codigoTarea;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCriteriosAceptacion() {
		return criteriosAceptacion;
	}
	public void setCriteriosAceptacion(String criteriosAceptacion) {
		this.criteriosAceptacion = criteriosAceptacion;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	public int getTiempoDesarrollo() {
		return tiempoDesarrollo;
	}
	public void setTiempoDesarrollo(int tiempoDesarrollo) {
		this.tiempoDesarrollo = tiempoDesarrollo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
	
}
