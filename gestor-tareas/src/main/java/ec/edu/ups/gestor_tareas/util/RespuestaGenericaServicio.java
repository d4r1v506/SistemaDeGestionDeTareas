package ec.edu.ups.gestor_tareas.util;

public class RespuestaGenericaServicio {

	 public RespuestaGenericaServicio(String state, Object data, String[] message) {
			super();
			this.state = state;
			this.data = data;
			this.message = message;
		}
	     
		public String state ;    
	    public Object data ;
	    public String[] message ;

		public String getState() {
			return state;
		}
		public Object getData() {
			return data;
		}
		public String[] getMessage() {
			return message;
		}
		public void setState(String state) {
			this.state = state;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public void setMessage(String[] message) {
			this.message = message;
		}

}
