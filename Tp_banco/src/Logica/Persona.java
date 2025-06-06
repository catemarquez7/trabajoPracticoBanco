package Logica;

public class Persona {

	protected String nombre;
	protected String domicilio;
	protected String mail;
	protected int documento;
	
	public Persona(String nombre, String domicilio, String mail, int documento) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.mail = mail;
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", domicilio=" + domicilio + ", mail=" + mail + ", documento=" + documento
				+ "]";
	}
	
}//final
