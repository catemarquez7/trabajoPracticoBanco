package Usuario;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extensiones.Inicio;
import Logica.Admin;
import Logica.Cliente;
import Logica.Usuario;

public class Main {

	public static void main(String[] args) {

		//variables
		int eleccion;
		
		//inicio
		Cliente.cargaClientes();
		Admin.cargaAdmins();
		JOptionPane.showMessageDialog(null, "Bienvenido al Banco de la Nación Argentina!", "BIENVENIDO!", JOptionPane.DEFAULT_OPTION,
				new ImageIcon(Main.class.getResource("/Img/banco.png")));
		do {	
			eleccion = JOptionPane.showOptionDialog(null, "Seleccione: ", "INICIO DE SESION", 0, 0,
					new ImageIcon(Main.class.getResource("/Img/inicio.png")), Inicio.values(), Inicio.values());
		switch (eleccion) {
		case 0: //iniciar sesion
			Usuario.login();
			break;
		case 1: //registrarse
			Usuario.registro();
			break;
		case 2: //salir
			JOptionPane.showMessageDialog(null, "Hasta luego! ", "ADIOS!", JOptionPane.DEFAULT_OPTION,
					new ImageIcon(Main.class.getResource("/Img/banco.png")));
			break;
		}//fin switch
		} while (eleccion != 2);
		
		
	}//fin main

}//final
