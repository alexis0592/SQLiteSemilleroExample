package com.example.sqliteexamplesemillero.process;

import com.example.sqliteexamplesemillero.entidades.User;

public interface IUserProcess {
	
	public User savePerson(User person);

	public User updatePerson(User person);

	public int deletePerson(User person);
	
	public User findPerson(String cedula);
}
