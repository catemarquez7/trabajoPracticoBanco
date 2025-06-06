package Logica;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extensiones.Validaciones;
import Usuario.Main;

public class Usuario extends Persona{

	protected String n_usuario;
	protected String contrasenia;
	static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	
	public Usuario(String nombre, String domicilio, String mail, int documento, String n_usuario, String contrasenia) {
		super(nombre, domicilio, mail, documento);
		this.n_usuario = n_usuario;
		this.contrasenia = contrasenia;
	}

	public String getN_usuario() {
		return n_usuario;
	}

	public void setN_usuario(String n_usuario) {
		this.n_usuario = n_usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public static LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(LinkedList<Usuario> usuarios) {
		Usuario.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Usuario [n_usuario=" + n_usuario + ", contraseña=" + contrasenia + "]";
	}
	
	public static void CargoUsuarios() {
		usuarios.add(new Usuario("Gamaliel", "Corrientes 2037", "gamaliel@davinci.edu.ar", 12345678, "Ghami", "Ghamiaprobanos@"));
	}
	
	public static void Login() {
		
	    Usuario existente = null;
	    boolean flag1 = false;
	    boolean flag2 = false;
	    
	    do {
			
	    	String ingreso = Validaciones.ValidarLetras("Ingrese su usuario:");
	    
	    for (Usuario user : usuarios) {
	        if (user.getN_usuario().equals(ingreso)) {
	            existente = user;
	            flag1 = true;
	            break;
	        }
	    }
	    if (existente == null) {
	        JOptionPane.showMessageDialog(null, "El usuario no existe, vuelva a intenrarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
	                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
	        flag1 = false;
	    }
	    } while (flag1 == false);
	    
	    do {
	    	
	    	String ingresop = Validaciones.ValidarContras("Ingrese su contraseña:");
	    
	    if (existente.getContrasenia().equals(ingresop)) {
	        JOptionPane.showMessageDialog(null, "Usuario correcto, bienvenido!", "BIENVENIDO!", JOptionPane.DEFAULT_OPTION,
	                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
	        flag2 = true;
	    } else {
	        JOptionPane.showMessageDialog(null, "Contraseña incorrecta.", "ERROR!", JOptionPane.DEFAULT_OPTION,
	                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
	        flag2 = false;
	    }
	    } while (flag2 == false);
	
	}//fin
	
	public static void Registro() {
		boolean flag = false;
		int dni;
		String nombre, domicilio, mail, documento, usuario, cont, cont2;
		
		nombre = Validaciones.ValidarLetras("Ingrese su nombre: ");
		domicilio = Validaciones.ValidarContras("Ingrese su domicilio: ");
		
		do {
			
			mail = Validaciones.ValidarContras("Ingrese su mail: ");
			flag = true;
			
		for (Usuario usu : usuarios) {
			if (mail.equals(usu.getMail())) {
				JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				flag = false;
				break;
			}
		}
		} while (flag == false);
		
		do {
		
			dni = Validaciones.ValidarNum("Ingrese su número de documento: ");
			documento = Integer.toString(dni);
			
			if (documento.length() == 8) {
				for (Usuario usu : usuarios) {
					if (dni == usu.getDocumento()) {
						JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
				                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
						flag = false;
						break;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Su número de documento debe tener 8 dígitos", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				flag = false;
			}
		} while (flag == false);
		
		
		do {
			
			usuario = Validaciones.ValidarContras("Ingrese su nombre de usuario: ");
			
		for (Usuario usu : usuarios) {
			if (usuario.equals(usu.getN_usuario())) {
				JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				flag = false;
				break;
			}
		}
		} while (flag == false);
		
		do {
			
		cont = Validaciones.ValidarContras("Ingrese su contraseña: ");
		cont2 = Validaciones.ValidarContras("Ingrese su contraseña nuevamente: ");
		
		if (cont.equals(cont2)) {
			JOptionPane.showMessageDialog(null, "Todos los datos son validos, su usuario fue registrado", "BIENVENIDO!", JOptionPane.DEFAULT_OPTION,
	                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
			usuarios.add(new Usuario(nombre, domicilio, mail, dni, usuario, cont));
			flag = true;
		} else {
			JOptionPane.showMessageDialog(null, "Las contraseñas no son identicas, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
	                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
			flag = false;
		}
		} while (flag == false);
		
	}//fin
	
}//final
