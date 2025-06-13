package Logica;

import java.util.LinkedList;

public class Cliente extends Usuario{

	//atributos
	protected int nro_cliente;
	
	//clase
	static LinkedList<Cliente> clientes = new LinkedList<Cliente>();

	
	public Cliente(String nombre, String domicilio, String mail, int documento, String n_usuario, String contrasenia,
			int nro_cliente) {
		super(nombre, domicilio, mail, documento, n_usuario, contrasenia);
		this.nro_cliente = nro_cliente;
	}

	//getters y setters
	public int getNro_cliente() {
		return nro_cliente;
	}

	public void setNro_cliente(int nro_cliente) {
		this.nro_cliente = nro_cliente;
	}
	
	//toString
	@Override
	public String toString() {
		return "Cliente [nro_cliente=" + nro_cliente + "]";
	}
	
	//metodos
	public static void cargaClientes() {
		Cliente c1 = new Cliente("Gamaliel", "Corrientes 2037", "gamaliel@davinci.edu.ar", 41667889, "ghami", "ghamiaprobanos1",(int)(Math.random()*100+1));
		Cliente c2 = new Cliente("Victoria", "Martin Fierro 1550", "vicotria@davinci.edu.ar", 46789087, "vicky", "12345678",(int)(Math.random()*100+1));
		Cliente c3 = new Cliente("Caterina", "Quesada 3849", "caterina@davinci.edu.ar", 46756432, "cate", "87654321",(int)(Math.random()*100+1));
		Cliente.clientes.add(c1);
		Cliente.clientes.add(c2);
		Cliente.clientes.add(c3);
		Cuenta.cuentas.add(new Cuenta(c1, 10000));
		Cuenta.cuentas.add(new Cuenta(c2, 25000));
		Cuenta.cuentas.add(new Cuenta(c3, 20000));

	}

	
	
	
	
}//clase
