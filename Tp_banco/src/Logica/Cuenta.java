package Logica;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extensiones.Validaciones;
import Usuario.Main;

public class Cuenta {

	//atributos
	protected Usuario usuario;
	protected double saldo;
	protected LinkedList<String> movimientos = new LinkedList<String>();
	
	//clase
	static LinkedList<Cuenta> cuentas = new LinkedList<Cuenta>();

	//constructor
	public Cuenta(Usuario usuario, double saldo) {
		this.usuario = usuario;
		this.saldo = saldo;
	}

	//getters y setters
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public LinkedList<String> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(LinkedList<String> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "Cuenta [usuario=" + usuario + ", saldo=" + saldo + ", movimientos=" + movimientos + "]";
	}
	
	//metodos
	public void transferir() {
		//variables
		String opcion="";
		double monto=0;
		LinkedList<Cuenta> disponibles = this.disponiblesTransferir();
		String[] disponiblesMenu = new String[disponibles.size()];
		for (int i = 0; i < disponibles.size(); i++) {
			String info = disponibles.get(i).getUsuario().getN_usuario();
			disponiblesMenu[i] = info;
		}
		
		//transferencia
		opcion = (String)JOptionPane.showInputDialog(null, "Seleccione el usuario al quien desea transferirle:", "SELECCION", 0, new ImageIcon(Main.class.getResource("/Img/prueba.png")), disponiblesMenu, disponiblesMenu[0]);	

		for (int i = 0; i < disponibles.size(); i++) {
			if (disponibles.get(i).getUsuario().getN_usuario().equals(opcion)) {
				JOptionPane.showMessageDialog(null, disponibles.get(i).getUsuario().getNombre() + " cuenta con $" + disponibles.get(i).getSaldo(), "TRANSFERENCIA", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				
				monto = this.ValidarSaldo("Escriba el monto a transferir. \nUsted cuenta con $" + this.getSaldo());
				
				disponibles.get(i).setSaldo(disponibles.get(i).getSaldo()+monto);
				this.setSaldo(this.getSaldo()-monto);
				
				JOptionPane.showMessageDialog(null, "Ahora " + disponibles.get(i).getUsuario().getNombre() + " cuenta con $" + disponibles.get(i).getSaldo() + "\n Y vos con $" + this.getSaldo(), "TRANSFERENCIA", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/prueba.png")));
			}
		}
		
	}
	
	public void depositar() {
		//variables
		String opcion="";
		double monto=0;
		String[] disponiblesMenu = new String[cuentas.size()];
		for (int i = 0; i < cuentas.size(); i++) {
			String info = cuentas.get(i).getUsuario().getN_usuario();
			disponiblesMenu[i] = info;
				}		

		//deposito	
		opcion = (String)JOptionPane.showInputDialog(null, "Seleccione el usuario al quien desea depositarle:", "SELECCION", 0, new ImageIcon(Main.class.getResource("/Img/prueba.png")), disponiblesMenu, disponiblesMenu[0]);	

				for (int i = 0; i < cuentas.size(); i++) {
					if (cuentas.get(i).getUsuario().getN_usuario().equals(opcion)) {
						
						JOptionPane.showMessageDialog(null, cuentas.get(i).getUsuario().getNombre() + " cuenta con $" + cuentas.get(i).getSaldo(), "DEPOSITO", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/prueba.png")));
						
						monto = Validaciones.ValidarNum("Escriba el monto a depositar.");
						
						cuentas.get(i).setSaldo(cuentas.get(i).getSaldo()+monto);
						
						JOptionPane.showMessageDialog(null, "Ahora " + cuentas.get(i).getUsuario().getNombre() + " cuenta con $" + cuentas.get(i).getSaldo(), "DEPOSITO", JOptionPane.DEFAULT_OPTION,new ImageIcon(Main.class.getResource("/Img/prueba.png")));
					}
				}
	}
	
	public void retirar() {
		JOptionPane.showMessageDialog(null, this.getUsuario().getNombre() + ", contas con con $" + this.getSaldo(), "EXTRACCION", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/prueba.png")));
		
		double monto = this.ValidarSaldo("Ingrese el monto a retirar:");
		
		this.setSaldo(this.getSaldo()-monto);
		
		JOptionPane.showMessageDialog(null, this.getUsuario().getNombre() + ", ahora contas con con $" + this.getSaldo(), "EXTRACCION", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/prueba.png")));
	}
	
	public void verMovimientos() {
		
	}
	
	public LinkedList<Cuenta> disponiblesTransferir(){
		
		LinkedList<Cuenta> disponibles = new LinkedList<Cuenta>();
		
		for (Cuenta cuenta : cuentas) {
			if (!cuenta.getUsuario().getN_usuario().equals(this.getUsuario().getN_usuario())) {
				disponibles.add(cuenta);
			}
		}
		return disponibles; 
	}
	
	
	//valida -0, enter, letras y !<saldo
	public int ValidarSaldo(String mensaje) {
		String data;
		boolean flag;
		int numero = 0;
		
		do {
			flag = true;
			do {
				data = (String) JOptionPane.showInputDialog(null, mensaje, "INGRESO", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Validaciones.class.getResource("/Img/prueba.png")), null, null);
				if (data.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puede haber espacios en blanco,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				}
			} while (data.isEmpty());
			for (int i = 0; i < data.length(); i++) {
				if (!Character.isDigit(data.charAt(i))) {
					JOptionPane.showMessageDialog(null, "No se pueden ingresar caracteres alfanumericos,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/prueba.png")));
					flag = false;
					break;
				}
			}
			if (flag) {
			    numero = Integer.parseInt(data);
			    if (numero <= 0) {
			    	JOptionPane.showMessageDialog(null, "No se pueden ingresar numeros menores a 0,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/prueba.png")));
			        flag = false;
			    }
			}
			
			if (flag) {
				if (numero <= this.getSaldo()) {
				 	JOptionPane.showMessageDialog(null, "Excelente! Se realizo con exito.", "LISTO!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/prueba.png")));
				} else {
					JOptionPane.showMessageDialog(null, "No puede ingresar un monto mayor a su saldo disponible.\nIntentelo nuevamente.", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/prueba.png")));
					flag = false;
				}
			}
			
		} while (!flag);
		
		return numero;
	}//fin

	
	
	
	
	
	
	
	
}
