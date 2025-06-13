package Logica;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extensiones.MenuAdmin;
import Usuario.Main;

public class Admin extends Usuario{

	//atributos
	protected int nro_empleado;

	//clase
	static LinkedList<Admin> administradores = new LinkedList<Admin>();

	
	//constructor
	public Admin(String nombre, String domicilio, String mail, int documento, String n_usuario, String contrasenia,
			int nro_empleado) {
		super(nombre, domicilio, mail, documento, n_usuario, contrasenia);
		this.nro_empleado = nro_empleado;
	}

	//getters y setters
	public int getNro_empleado() {
		return nro_empleado;
	}

	public void setNro_empleado(int nro_empleado) {
		this.nro_empleado = nro_empleado;
	}

	//toString
	@Override
	public String toString() {
		return "Admin [nro_empleado=" + nro_empleado + "]";
	}
	
	//metodos
	public static void cargaAdmins() {
		Admin a1 = new Admin("Juana", "Chubut 238", "rebe123@davinci.edu.ar", 43556987, "juana", "admin1111",(int)(Math.random()*100+1));
		Admin a2 = new Admin("Rebeca", "Chubut 238", "rebe123@davinci.edu.ar", 43556987, "rebeca", "admin2222",(int)(Math.random()*100+1));
		administradores.add(a1);
		administradores.add(a2);

	}
	
	public static void menuAdmin(Admin ad) {
		int eleccion1=0;
	    
    	do { //menu principal
		eleccion1 = JOptionPane.showOptionDialog(null, "Seleccione: \nEmpleado: " + ad.getNombre(), "INICIO", 0, 0,
				new ImageIcon(Main.class.getResource("/Img/prueba.png")), MenuAdmin.values(), MenuAdmin.values());
		
		switch (eleccion1) {
		
		case 0: //bloquear cliente
			bloquearCliente();
			break;

		case 1: //ver movimientos
			verMovimientos();
			break;
			
		case 2: //cerrar sesion
			JOptionPane.showMessageDialog(null, "Su sesion ha finalizado. ", "ADIOS!", JOptionPane.DEFAULT_OPTION,
					new ImageIcon(Main.class.getResource("/Img/prueba.png")));
			break;
			
		}//fin switch
		
		} while (eleccion1 != 2);
	}
	
	public static void bloquearCliente() {
		//variables
		String[] disponiblesMenu = new String[Cuenta.cuentas.size()];
		for (int i = 0; i < Cuenta.cuentas.size(); i++) {
			String info = Cuenta.cuentas.get(i).getCliente().getN_usuario();
			disponiblesMenu[i] = info;
				}	
		//funcion
		String opcion = (String)JOptionPane.showInputDialog(null, "Seleccione el cliente al cual debe bloquear:", "SELECCION", 0, new ImageIcon(Main.class.getResource("/Img/prueba.png")), disponiblesMenu, disponiblesMenu[0]);	

		for (int i = 0; i < Cuenta.cuentas.size(); i++) {
			if (Cuenta.cuentas.get(i).getCliente().getN_usuario().equals(opcion)) {
				Cuenta.cuentas.get(i).setEstado("Bloqueado");
			JOptionPane.showMessageDialog(null, "La cuenta de  " +  Cuenta.cuentas.get(i).getCliente().getN_usuario() + " ha sido bloqueada con exito.", "LISTO!", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/prueba.png")));	

			}
		}
	}
	
	public static void verMovimientos() {
		//variables
			String[] disponiblesMenu = new String[Cuenta.cuentas.size()];
			for (int i = 0; i < Cuenta.cuentas.size(); i++) {
				String info = Cuenta.cuentas.get(i).getCliente().getN_usuario();
				disponiblesMenu[i] = info;
						}	
			//funcion
			String opcion = (String)JOptionPane.showInputDialog(null, "Seleccione el cliente del cual quiere ver sus movimientos:", "SELECCION", 0, new ImageIcon(Main.class.getResource("/Img/prueba.png")), disponiblesMenu, disponiblesMenu[0]);	

			for (int i = 0; i < Cuenta.cuentas.size(); i++) {
				if (Cuenta.cuentas.get(i).getCliente().getN_usuario().equals(opcion)) {
					Cuenta.cuentas.get(i).movimientos();

					}
				}
	}
		
	
}//clase
