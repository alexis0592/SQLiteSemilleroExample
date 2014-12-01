package com.example.sqliteexamplesemillero.process.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.sqliteexamplesemillero.dao.IUserDAO;
import com.example.sqliteexamplesemillero.dao.impl.UserDAOImpl;
import com.example.sqliteexamplesemillero.entidades.User;
import com.example.sqliteexamplesemillero.process.IUserProcess;
import com.example.sqliteexamplesemillero.sqlite.contract.PersistanceContract;
import com.example.sqliteexamplesemillero.sqlite.contract.UserContract;

public class UserProcessImpl implements IUserProcess {

	private static final String TAG = UserProcessImpl.class.getSimpleName();
	private IUserDAO userDAO;

	public UserProcessImpl(Context context) {

		super();
		userDAO = UserDAOImpl.getInstance(context);
	}

	@Override
	public User savePerson(User user) {
		Log.i(TAG, "savePerson()");

		return ((this.userDAO.savePerson(this.convertUserToContentValues(user)) != null) ? user
				: null);
	}

	@Override
	public User updatePerson(User user) {
		Log.i(TAG, "updateUser()");

		String whereClause = String.format("%s = ?", UserContract.Colum.CEDULA);
		String[] whereArgs = new String[] { user.getCedula() };

		return ((this.userDAO.updatePerson(
				this.convertUserToContentValues(user), whereClause, whereArgs) != null) ? user
				: null);
	}

	@Override
	public int deletePerson(User user) {
		Log.i(TAG, "deletePerson()");

		String whereClause = String.format("%s = ?", UserContract.Colum.CEDULA);
		String[] whereArgs = new String[] { user.getCedula() };

		return (this.userDAO.deletePersons(whereClause, whereArgs));
	}

	private ContentValues convertUserToContentValues(User user) {
		ContentValues contentValues = new ContentValues();

		contentValues.put(UserContract.Colum.CEDULA, user.getCedula());
		contentValues.put(UserContract.Colum.EDAD, user.getEdad());
		contentValues.put(UserContract.Colum.NOMBRE, user.getNombre());
		contentValues.put(UserContract.Colum.TELEFONO, user.getTel());

		return contentValues;
	}

	@Override
	public User findPerson(String cedula) {

		Log.i(TAG, "findPerson()");

		List<User> listUsers = new ArrayList<User>();
		
		
		List<ContentValues> contentValuesList = this.userDAO.findUser(cedula);

		for(ContentValues contentValues: contentValuesList){
			try {
				listUsers.add(this.convertContentValueToUser(contentValues));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// User user = converContentValueToUser(contentValues);

		return listUsers.get(0);
	}
	
	private User convertContentValueToUser(ContentValues contentValues){
		
		User user = new User();
			
		user.setCedula(contentValues.getAsString(UserContract.Colum.CEDULA));
		user.setEdad(contentValues.getAsString(UserContract.Colum.EDAD));
		user.setNombre(contentValues.getAsString(UserContract.Colum.NOMBRE));
		user.setTel(contentValues.getAsString(UserContract.Colum.TELEFONO));
		
		return user;
	}

}
