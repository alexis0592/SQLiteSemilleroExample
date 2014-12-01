package com.example.sqliteexamplesemillero.dao;

import java.util.List;

import android.content.ContentValues;

public interface IUserDAO {

	public ContentValues savePerson(ContentValues personContentValues);

	public ContentValues updatePerson(ContentValues personContentValues,
			String whereClause, String[] whereArgs);

	public Integer deletePersons(String whereClause, String[] whereArgs);
	
	public List<ContentValues> findUser(String cedula); 

}
