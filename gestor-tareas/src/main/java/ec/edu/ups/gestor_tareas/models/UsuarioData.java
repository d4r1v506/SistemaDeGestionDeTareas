package ec.edu.ups.gestor_tareas.models;

public class UsuarioData {
	private int id;
	private String identificacion;
	private String nombres;
	private String apellidos;
	private int edad;
	private String cargo;
	private UsuarioEstado estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public UsuarioEstado getEstado() {
		return estado;
	}
	public void setEstado(UsuarioEstado estado) {
		this.estado = estado;
	}
	
	
}