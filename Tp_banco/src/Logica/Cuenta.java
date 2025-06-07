package Logica;

import java.util.LinkedList;

public class Cuenta {

	protected Usuario usuario;
	protected double saldo;
	protected LinkedList<String> movimientos = new LinkedList<String>();
	
	static LinkedList<Cuenta> cuentas = new LinkedList<Cuenta>();

	
	public Cuenta(Usuario usuario, double saldo) {
		this.usuario = usuario;
		this.saldo = saldo;
	}

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
	
	public void transferir() {
		// lista de opciones con todas las cuentas menos la propia
	}
	
	public void depositar() {
		// lista de opciones con todas las cuentas

	}
	
	public void retirar() {
		
	}
	
	public void verMovimientos() {
		
	}
}
