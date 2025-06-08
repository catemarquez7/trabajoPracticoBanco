package Logica;

import java.util.LinkedList;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extensiones.Menu;
import Extensiones.Validaciones;
import Usuario.Main;

public class Usuario extends Persona{
	//atributos
	protected String n_usuario;
	protected String contrasenia;
	
	//clase
	static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	
	//constructor
	public Usuario(String nombre, String domicilio, String mail, int documento, String n_usuario, String contrasenia) {
		super(nombre, domicilio, mail, documento);
		this.n_usuario = n_usuario;
		this.contrasenia = contrasenia;
	}

	//getters y setters
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
	
	//metodos
	public static void CargaUsuarios() {
		Usuario u1 = new Usuario("Gamaliel", "Corrientes 2037", "gamaliel@davinci.edu.ar", 12345678, "ghami", "ghamiaprobanos1");
		Usuario u2 = new Usuario("Victoria", "Martin Fierro 1550", "vicotria@davinci.edu.ar", 46789087, "vicky", "12345678");
		Usuario u3 = new Usuario("Caterina", "Quesada 3849", "caterina@davinci.edu.ar", 46756432, "cate", "87654321");
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		Cuenta.cuentas.add(new Cuenta(u1, 10000));
		Cuenta.cuentas.add(new Cuenta(u2, 25000));
		Cuenta.cuentas.add(new Cuenta(u3, 20000));

	}
	
	public static void Login() {
		//variables
	    Usuario existente = null;
	    Cuenta log = null;
	    boolean flag1 = false;
	    boolean flag2 = false;
	    
	    //login
	    do {
			
	    	String ingreso = Validaciones.ValidarLetras("Ingrese su usuario:");
	    
	    for (Usuario user : usuarios) {
	        if (user.getN_usuario().equals(ingreso)) {
	            existente = user;
	            for (Cuenta cuenta : Cuenta.cuentas) {
					if (cuenta.getUsuario().equals(user)) {
						log = cuenta;
					}
				}
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
	    	
	    	String ingreso = Validaciones.ValidarContras("Ingrese su contraseña:");
	    
	    if (existente.getContrasenia().equals(ingreso)) {
	        JOptionPane.showMessageDialog(null, "Usuario correcto, bienvenido/a " + existente.getNombre() + "!" ,"BIENVENIDO!", JOptionPane.DEFAULT_OPTION,
	                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
	        flag2 = true;
	    } else {
	        JOptionPane.showMessageDialog(null, "Contraseña incorrecta.", "ERROR!", JOptionPane.DEFAULT_OPTION,
	                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
	        flag2 = false;
	    }
	    } while (flag2 == false);
	
	    
	    //inicio programa
	    int eleccion1=0;
	    
	    	do { //menu principal
			
			eleccion1 = JOptionPane.showOptionDialog(null, "Seleccione: \n Titular: " + log.getUsuario().getNombre() + "\n Saldo actual $" + log.getSaldo() , "INICIO", 0, 0,
					new ImageIcon(Main.class.getResource("/Img/prueba.png")), Menu.values(), Menu.values());
			
			switch (eleccion1) {
			
			case 0: //depositar
				log.depositar();
				break;

			case 1: //retirar
				log.retirar();
				break;
				
			case 2: //transferir
				log.transferir();
				break;

			case 3: //ver movimientos
				log.movimientos();
				break;
				
			case 4: //cerrar sesion
				JOptionPane.showMessageDialog(null, "Su sesion ha finalizado. ", "ADIOS!", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				break;
				
			}//fin switch
			
			} while (eleccion1 != 4);
	    
	}//fin
	
	public static void Registro() {
		
		String nombre, domicilio, mail, usuario, cont;
		int dni;
		
		nombre = Validaciones.ValidarLetras("Ingrese su nombre: ");
		domicilio = Validaciones.ValidarContras("Ingrese su domicilio: ");
		mail = Usuario.IngresoMail();
		dni = Usuario.IngresoDni();
		usuario = Usuario.IngresoUser();
		cont = Usuario.IngresoContra();
		
		Usuario u = new Usuario(nombre, domicilio, mail, dni, usuario, cont);
		usuarios.add(u);
		Cuenta.cuentas.add(new Cuenta(u, 15000));		
		
		
	}//fin
	
	public static String IngresoMail() {
		
		String mail;
		boolean flag = false;
		
		do {
			
			mail = Validaciones.ValidarContras("Ingrese su mail: ");
			
			if (mail.contains("@") && mail.contains(".com")) {
				for (Usuario usu : usuarios) {
					if (mail.equals(usu.getMail())) {
						JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
				                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
						flag = false;
						break;
					}else {
						flag = true;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "El mail ingresado no es válido (falta @ o .com)", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				flag = false;
			}
		} while (flag == false);
		
	return mail;
	
	}//fin
	
	public static int IngresoDni() {
		
		String documento;
		int dni;
		boolean flag = false;
		
		do {
			
			dni = Validaciones.ValidarNum("Ingrese su número de documento: ");
			documento = Integer.toString(dni);
			
			if (documento.length() >= 8) {
				for (Usuario usu : usuarios) {
					if (dni == usu.getDocumento()) {
						JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
				                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
						flag = false;
						break;
					}else {
						flag = true;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Su número de documento debe tener mas de 8 dígitos.", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				flag = false;
			}
		} while (flag == false);
		
	return dni;
	
	}//fin
	
	public static String IngresoUser() {
		
		String usuario;
		boolean flag = false;
		
		do {
			
			usuario = Validaciones.ValidarLetras("Ingrese su nombre de usuario:\n(No puede ingresar números en su nombre de usuario) ");
			
		for (Usuario usu : usuarios) {
			if (usuario.equals(usu.getN_usuario())) {
				JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				flag = false;
				break;
			}else {
				flag = true;
			}
		}
		} while (flag == false);
		
	return usuario;	
	
	}//fin
	
	public static String IngresoContra() {
	
		String cont, cont2;
		boolean flag = false;
		
		do {
			
			cont = Validaciones.ValidarContras("Ingrese su contraseña:\n(Debe tener 8 dígitos)");
			
			if (cont.length() >= 8) {
				cont2 = Validaciones.ValidarContras("Ingrese su contraseña nuevamente: ");
				
				if (cont.equals(cont2)) {
					JOptionPane.showMessageDialog(null, "Todos los datos son válidos, su usuario fue registrado", "BIENVENIDO!", JOptionPane.DEFAULT_OPTION,
			                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
					flag = true;
				} else {
					JOptionPane.showMessageDialog(null, "Las contraseñas no son idénticas, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
			                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
					flag = false;
				}
			}else {
				JOptionPane.showMessageDialog(null, "Su contraseña debe tener 8 dígitos", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				flag = false;
			}
			
			} while (flag == false);
		
	return cont;
	
	}//fin
	
	
	
}//final
