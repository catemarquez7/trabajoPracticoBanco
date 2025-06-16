package Logica;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extensiones.Validaciones;
import Usuario.Main;

public abstract class Usuario extends Persona{
	//atributos
	protected String n_usuario;
	protected String contrasenia;
		
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

	//toString
	@Override
	public String toString() {
		return "Usuario [n_usuario=" + n_usuario + ", contraseña=" + contrasenia + "]";
	}
	
	//metodos
	
	public static void login() {
		//variables
	    Cliente existenteCl = null;
	    Admin existenteAd = null;
	    Cuenta log = null;
	    boolean flagUsu1 = false;
	    boolean flagUsu2 = false;
	    String l = "No";
	    
	    //check n_usuario
	    do {
			
	    	String ingreso = Validaciones.ValidarLetras("Ingrese su usuario:");
	    
	    for (Cliente user : Cliente.clientes) {
	        if (user.getN_usuario().equals(ingreso)) {
	            existenteCl = user;
	            l =  "Cliente";
	            flagUsu2 = true;
	            for (Cuenta cuenta : Cuenta.cuentas) {
					if (cuenta.getCliente().equals(user)) {
						if (cuenta.getEstado().equals("Bloqueado")) {
							l = "Bloqueado";
							break;
						} else {
							log = cuenta;
						}
					}
				}
	            flagUsu1 = true;
	            break;
	        } 
	    }
	    
	    if (!flagUsu1) {
			for (Admin ad : Admin.administradores) {
				if (ad.getN_usuario().equals(ingreso)) {
					existenteAd = ad;
					l = "Admin";
					flagUsu2 = true;
					break;
				} 
			}
		}
	    
	    if (!flagUsu2) {
	        JOptionPane.showMessageDialog(null, "El usuario no existe, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
	                new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
	        flagUsu2 = false;
	    }
	    
	    
	    } while (!flagUsu2);
	    
	    
	    //check contra
	    boolean flagContra1 = false;
	    boolean flagContra2 = false;

	    
	    switch (l) {
		case "Cliente":
			 //cliente
		    do {
		    	
		    	String ingreso = Validaciones.ValidarContras("Ingrese su contraseña:");
		    
		    if (existenteCl.getContrasenia().equals(ingreso)) {
		        JOptionPane.showMessageDialog(null, "Usuario correcto, bienvenido/a " + existenteCl.getNombre() + "!" ,"BIENVENIDO!", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/sivalidacion.png")));
		        flagContra1 = true;
		        Cuenta.menuCliente(log); 
		        break;
		        
		    } 
		    
		    if (!flagContra1) {
		    	 JOptionPane.showMessageDialog(null, "Contraseña incorrecta.", "ERROR!", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
			        flagContra1 = false;
			}
		    
		    } while (!flagContra1);
		
			break;

		case "Admin":
			  //admin
		    do {
		    	
		    	String ingreso = Validaciones.ValidarContras("Ingrese su contraseña:");
		    
		    	if (existenteAd.getContrasenia().equals(ingreso)) {
		    		JOptionPane.showMessageDialog(null, "Usuario correcto, bienvenido administrador/a " + existenteAd.getNombre() + "!" ,"BIENVENIDO!", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/sivalidacion.png")));
		    		flagContra2 = true;
		    		Admin.menuAdmin(existenteAd);
		    		break;
		    		
		    	} 
		    
		    if (!flagContra2) {
		    	 JOptionPane.showMessageDialog(null, "Contraseña incorrecta.", "ERROR!", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
			        flagContra1 = false;
			}
		    
		    } while (!flagContra2);
		    
			break;
			
		case "Bloqueado":
			do {
				
			String ingreso = Validaciones.ValidarContras("Ingrese su contraseña:");
			if (existenteCl.getContrasenia().equals(ingreso)) {
	    		flagContra2 = true;
	    		JOptionPane.showMessageDialog(null, "Su cuenta se encuentra bloqueada, por favor comuniquese \ncon un asesor en la sucursal mas cercana.", "ERROR!", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/bloqueo.png")));
	    		break;
				}
			} while (!flagContra2);
		}
	      
	}//funcion
	
	public static void registro() {
		
		String nombre, domicilio, mail, usuario, cont;
		int dni;
		
		nombre = Validaciones.ValidarLetras("Ingrese su nombre: ");
		domicilio = Validaciones.ValidarContras("Ingrese su domicilio: ");
		mail = Usuario.ingresoMail();
		dni = Usuario.ingresoDni();
		usuario = Usuario.ingresoUser();
		cont = Usuario.ingresoContra();
		
		Cliente c = new Cliente(nombre, domicilio, mail, dni, usuario, cont, (int)(Math.random()*100+1));
		Cliente.clientes.add(c);
		Cuenta.cuentas.add(new Cuenta(c, 15000));		
		
		
	}//fin
	
	public static String ingresoMail() {
		
		String mail;
		boolean flag = false;
		
		do {
			
			mail = Validaciones.ValidarContras("Ingrese su mail: ");
			
			if (mail.contains("@") && mail.contains(".com")) {
				for (Cliente cli : Cliente.clientes) {
					if (mail.equals(cli.getMail())) {
						JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
				                new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
						flag = false;
						break;
					}else {
						flag = true;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "El mail ingresado no es válido (falta @ o .com)", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
				flag = false;
			}
		} while (flag == false);
		
	return mail;
	
	}//fin
	
	public static int ingresoDni() {
		
		String documento;
		int dni;
		boolean flag = false;
		
		do {
			
			dni = Validaciones.ValidarNum("Ingrese su número de documento: ");
			documento = Integer.toString(dni);
			
			if (documento.length() >= 8) {
				for (Cliente cli : Cliente.clientes) {
					if (dni == cli.getDocumento()) {
						JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
				                new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
						flag = false;
						break;
					}else {
						flag = true;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Su número de documento debe tener mas de 8 dígitos.", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
				flag = false;
			}
		} while (flag == false);
		
	return dni;
	
	}//fin
	
	public static String ingresoUser() {
		
		String usuario;
		boolean flag = false;
		
		do {
			
			usuario = Validaciones.ValidarLetras("Ingrese su nombre de usuario:\n(No puede ingresar números en su nombre de usuario) ");
			
		for (Cliente cli : Cliente.clientes) {
			if (usuario.equals(cli.getN_usuario())) {
				JOptionPane.showMessageDialog(null, "Usuario ya registrado, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
				flag = false;
				break;
			}else {
				flag = true;
			}
		}
		} while (flag == false);
		
	return usuario;	
	
	}//fin
	
	public static String ingresoContra() {
	
		String cont, cont2;
		boolean flag = false;
		
		do {
			
			cont = Validaciones.ValidarContras("Ingrese su contraseña:\n(Debe tener 8 dígitos)");
			
			if (cont.length() >= 8) {
				cont2 = Validaciones.ValidarContras("Ingrese su contraseña nuevamente: ");
				
				if (cont.equals(cont2)) {
					JOptionPane.showMessageDialog(null, "Todos los datos son válidos, su usuario fue registrado", "BIENVENIDO!", JOptionPane.DEFAULT_OPTION,
			                new ImageIcon(Main.class.getResource("/Img/sivalidacion.png")));
					flag = true;
				} else {
					JOptionPane.showMessageDialog(null, "Las contraseñas no son idénticas, vuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
			                new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
					flag = false;
				}
			}else {
				JOptionPane.showMessageDialog(null, "Su contraseña debe tener 8 dígitos", "ERROR!", JOptionPane.DEFAULT_OPTION,
		                new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
				flag = false;
			}
			
			} while (flag == false);
		
	return cont;
	
	}//fin
	
	
	
}//final
