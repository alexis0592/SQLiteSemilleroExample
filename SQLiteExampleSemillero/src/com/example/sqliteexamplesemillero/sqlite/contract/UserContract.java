package com.example.sqliteexamplesemillero.sqlite.contract;

public class UserContract {

	public static final String TABLE_NAME = "USUARIO";

	public UserContract() {
		super();
	}

	public static final class Colum {

		public static final String NOMBRE = "nombre";
		public static final String EDAD = "edad";
		public static final String TELEFONO = "telefono";
		public static final String CEDULA = "cedula";

		public static final String[] getAllColumns() {

			return (new String[] { NOMBRE, EDAD, TELEFONO, CEDULA });
		}
	}
}
