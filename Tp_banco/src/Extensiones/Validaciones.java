package Extensiones;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Usuario.Main;

public class Validaciones {
	
//validacion de ingreso de numeros (-0, enter, letras)
	
	public static int ValidarNum(String mensaje) {
		String data;
		boolean flag;
		int numero = 0;
		
		do {
			flag = true;
			do {
				data = (String) JOptionPane.showInputDialog(null, mensaje, "INGRESO", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Validaciones.class.getResource("/Img/validacion.png")), null, null);
				if (data.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puede haber espacios en blanco,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
				}
			} while (data.isEmpty());
			for (int i = 0; i < data.length(); i++) {
				if (!Character.isDigit(data.charAt(i))) {
					JOptionPane.showMessageDialog(null, "No se pueden ingresar caracteres alfanuméricos,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
					flag = false;
					break;
				}
			}
			if (flag) {
			    numero = Integer.parseInt(data);
			    if (numero <= 0) {
			    	JOptionPane.showMessageDialog(null, "No se pueden ingresar números menores a 0,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
			        flag = false;
			    }
			}
		} while (!flag);
		
		return numero;
	}//fin

//validacion de ingreso de palabras (enter, nros)
	
	public static String ValidarLetras(String mensaje) {
		
		String data;
		boolean flag;
		
		do {
			flag = true;
		do {
			data = (String) JOptionPane.showInputDialog(null, mensaje, "INGRESO", JOptionPane.DEFAULT_OPTION,
					new ImageIcon(Validaciones.class.getResource("/Img/validacion.png")), null, null);
			if (data.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No puede haber espacios en blanco,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
			}
		} while (data.isEmpty());
		for (int i = 0; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i))) {
            	JOptionPane.showMessageDialog(null, "No se pueden ingresar números,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
                flag = false;
                break;
            }
        }
		} while (!flag);
		
		return data;
	}//fin
	
//validacion de ingreso de palabras (enter)	

	public static String ValidarContras(String mensaje) {
		
		String data;
		
		do {
			data = (String) JOptionPane.showInputDialog(null, mensaje, "INGRESO", JOptionPane.DEFAULT_OPTION,
					new ImageIcon(Validaciones.class.getResource("/Img/validacion.png")), null, null);
			if (data.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No puede haber espacios en blanco,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
			}
		} while (data.isEmpty());

		return data;

	}
 

}//final
