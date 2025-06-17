package Logica;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Extensiones.MenuCliente;
import Extensiones.Validaciones;
import Usuario.Main;

public class Cuenta {

	//atributos
	protected Cliente cliente;
	protected double saldo;
	protected String estado;
	protected LinkedList<String> movimientos = new LinkedList<String>();
	
	//clase
	static LinkedList<Cuenta> cuentas = new LinkedList<Cuenta>();


	//constructor
	public Cuenta(Cliente cliente, double saldo) {
		this.cliente = cliente;
		this.saldo = saldo;
		this.estado = "Activo";
	}

	//getters y setters
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static LinkedList<Cuenta> getCuentas() {
		return cuentas;
	}

	public static void setCuentas(LinkedList<Cuenta> cuentas) {
		Cuenta.cuentas = cuentas;
	}

	public LinkedList<String> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(LinkedList<String> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "Cuenta [clente=" + cliente + ", saldo=" + saldo + ", movimientos=" + movimientos + "]";
	}
	
	//metodos
	
	public static void menuCliente(Cuenta log) {
		 int eleccion1=0;
		    
	    	do { //menu principal
			
			eleccion1 = JOptionPane.showOptionDialog(null, "Seleccione: \nTitular: " + log.getCliente().getNombre() + "\nSaldo actual $" + log.getSaldo() , "INICIO", 0, 0,
					new ImageIcon(Main.class.getResource("/Img/cliente.png")), MenuCliente.values(), MenuCliente.values());
			
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
						new ImageIcon(Main.class.getResource("/Img/banco.png")));
				break;
				
			}//fin switch
			
			} while (eleccion1 != 4);
	}
	
	public void transferir() {
		//variables
		String opcion="";
		double monto=0;
		LinkedList<Cuenta> disponibles = this.disponiblesTransferir();
		String[] disponiblesMenu = new String[disponibles.size()];
		for (int i = 0; i < disponibles.size(); i++) {
			String info = disponibles.get(i).getCliente().getN_usuario();
			disponiblesMenu[i] = info;
		}
		
		//transferencia
		opcion = (String)JOptionPane.showInputDialog(null, "Seleccione el usuario al quien desea transferirle:", "SELECCION", 0, new ImageIcon(Main.class.getResource("/Img/cliente.png")), disponiblesMenu, disponiblesMenu[0]);	

		for (int i = 0; i < disponibles.size(); i++) {
			if (disponibles.get(i).getCliente().getN_usuario().equals(opcion)) {
				JOptionPane.showMessageDialog(null, disponibles.get(i).getCliente().getNombre() + " cuenta con $" + disponibles.get(i).getSaldo(), "TRANSFERENCIA", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/tranferencia.png")));
				
				monto = this.ValidarSaldo("Escriba el monto a transferir.\nUsted cuenta con $" + this.getSaldo());
				
				disponibles.get(i).setSaldo(disponibles.get(i).getSaldo()+monto);
				this.setSaldo(this.getSaldo()-monto);
				
				//registro movimientos
				this.movimientos.add("Transferiste $" + monto + " a " + disponibles.get(i).getCliente().getN_usuario());
				disponibles.get(i).getMovimientos().add("Recibiste $" + monto + " de " + this.getCliente().getN_usuario());
				
				JOptionPane.showMessageDialog(null, "Ahora " + disponibles.get(i).getCliente().getNombre() + " cuenta con $" + disponibles.get(i).getSaldo() + "\n Y vos con $" + this.getSaldo(), "TRANSFERENCIA", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/tranferencia.png")));
			}
		}
		
	}
	
	public void depositar() {
		//variables
		String opcion="";
		double monto=0;
		String[] disponiblesMenu = new String[cuentas.size()];
		for (int i = 0; i < cuentas.size(); i++) {
			String info = cuentas.get(i).getCliente().getN_usuario();
			disponiblesMenu[i] = info;
				}		

		//deposito	
		opcion = (String)JOptionPane.showInputDialog(null, "Seleccione el usuario al quien desea depositarle:", "SELECCION", 0, new ImageIcon(Main.class.getResource("/Img/cliente.png")), disponiblesMenu, disponiblesMenu[0]);	

				for (int i = 0; i < cuentas.size(); i++) {
					if (cuentas.get(i).getCliente().getN_usuario().equals(opcion)) {
						
						JOptionPane.showMessageDialog(null, cuentas.get(i).getCliente().getNombre() + " cuenta con $" + cuentas.get(i).getSaldo(), "DEPOSITO", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/deposito.png")));
						
						monto = Validaciones.ValidarNum("Escriba el monto a depositar.");
						
						cuentas.get(i).setSaldo(cuentas.get(i).getSaldo()+monto);
						
						//registro movimientos
						this.movimientos.add("Depositaste $" + monto + " a " + cuentas.get(i).getCliente().getN_usuario());
						cuentas.get(i).getMovimientos().add("Te depositaron $" + monto + " desde " + this.getCliente().getN_usuario());
						
						JOptionPane.showMessageDialog(null, "Ahora " + cuentas.get(i).getCliente().getNombre() + " cuenta con $" + cuentas.get(i).getSaldo(), "DEPOSITO", JOptionPane.DEFAULT_OPTION,new ImageIcon(Main.class.getResource("/Img/deposito.png")));
					}
				}
	
	
	}
	
	public void retirar() {
		JOptionPane.showMessageDialog(null, this.getCliente().getNombre() + ", contas con con $" + this.getSaldo(), "EXTRACCION", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/cliente.png")));
		
		double monto = this.ValidarSaldo("Ingrese el monto a retirar:");
		
		this.setSaldo(this.getSaldo()-monto);
		
		JOptionPane.showMessageDialog(null, this.getCliente().getNombre() + ", ahora contas con con $" + this.getSaldo(), "EXTRACCION", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/retirar.png")));
		
		//registro movimientos
		this.movimientos.add("Retiraste $" + monto);
		
	}
	
	public void movimientos() {
		
		if (movimientos.isEmpty()) {
			
	        JOptionPane.showMessageDialog(null, "No hay movimientos registrados.", "MOVIMIENTOS", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/movimientos.png")));
	        
	    } else {
	    	
	    	String historial = "Historial de movimientos:\n";
	        for (String mov : movimientos) {
	            historial += mov + "\n";
	            
	        }
	        JOptionPane.showMessageDialog(null, historial.toString(), "MOVIMIENTOS", JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("/Img/movimientos.png")));
	    }
	}
	
	public LinkedList<Cuenta> disponiblesTransferir(){
		
		LinkedList<Cuenta> disponibles = new LinkedList<Cuenta>();
		
		for (Cuenta cuenta : cuentas) {
			if (!cuenta.getCliente().getN_usuario().equals(this.getCliente().getN_usuario())) {
				disponibles.add(cuenta);
			}
		}
		return disponibles; 
	}
	
	//valida -0, enter, letras y !<saldo
	public double ValidarSaldo(String mensaje) {
		String data;
		boolean flag;
		double numero = 0;
		
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
			    numero = Double.parseDouble(data);
			    if (numero <= 0) {
			    	JOptionPane.showMessageDialog(null, "No se pueden ingresar numeros menores a 0,\nvuelva a intentarlo", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
			        flag = false;
			    }
			}
			
			if (flag) {
				if (numero <= this.getSaldo()) {
				 	JOptionPane.showMessageDialog(null, "Excelente! Se realizó con éxito.", "LISTO!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/sivalidacion.png")));
				} else {
					JOptionPane.showMessageDialog(null, "No puede ingresar un monto mayor a su saldo disponible.\nInténtelo nuevamente.", "ERROR!", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Main.class.getResource("/Img/novalidacion.png")));
					flag = false;
				}
			}
			
		} while (!flag);
		
		return numero;
	}//fin

	
	
	
	
	
	
	
	
}
