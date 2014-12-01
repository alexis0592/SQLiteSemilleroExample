package com.example.sqliteexamplesemillero.entidades;

public class User {

	private String cedula;
	private String nombre;
	private String edad;
	private String tel;

	public User(String cedula, String nombre, String edad, String tel) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
		this.tel = tel;
	}
	
	public User(){
		
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
