package Usuario;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extensiones.Inicio;
import Extensiones.Menu;
import Logica.Usuario;

public class Main {

	public static void main(String[] args) {

		int eleccion, eleccion1;
		boolean ingreso;
		Usuario.CargoUsuarios();
		
		JOptionPane.showMessageDialog(null, "Bienvenido a ", "BIENVENIDO!", JOptionPane.DEFAULT_OPTION,
				new ImageIcon(Main.class.getResource("/Img/prueba.png")));
		
		eleccion = JOptionPane.showOptionDialog(null, "Seleccione: ", "INICIO DE SESION", 0, 0,
				new ImageIcon(Main.class.getResource("/Img/prueba.png")), Inicio.values(), Inicio.values());
		
		do {
			
		switch (eleccion) {
		
		case 0: //iniciar sesion
			
			Usuario.Login();
			
			break;

		case 1: //registrarse
			
			Usuario.Registro();
			
			break;
			
		}//fin switch
		
		} while (eleccion != 0);
		
		eleccion1 = JOptionPane.showOptionDialog(null, "Seleccione: ", "INICIO DE SESION", 0, 0,
				new ImageIcon(Main.class.getResource("/Img/prueba.png")), Menu.values(), Menu.values());
		
		do {
			
			switch (eleccion1) {
			
			case 0: //depositar
				
				break;

			case 1: //retirar
				
				break;
				
			case 2: //transferir
				
				break;

			case 3: //ver movimientos
				
				break;
				
			case 4: //salir
				
				JOptionPane.showMessageDialog(null, " ", "ADIOS!", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Main.class.getResource("/Img/.png")));
				
				break;
				
			}//fin switch
			
			} while (eleccion != 4);
		
		
		
	}//fin main

}//final
