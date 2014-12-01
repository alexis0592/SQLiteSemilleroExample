package com.example.sqliteexamplesemillero.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqliteexamplesemillero.dao.IUserDAO;
import com.example.sqliteexamplesemillero.sqlite.AccesorSQLiteOpenHelper;
import com.example.sqliteexamplesemillero.sqlite.contract.UserContract;

public class UserDAOImpl implements IUserDAO {

	private static final String TAG = UserDAOImpl.class.getSimpleName();

	private AccesorSQLiteOpenHelper accesorSQLiteOpenHelper;
	private static UserDAOImpl instance = null;

	private UserDAOImpl(Context context) {
		super();
		this.accesorSQLiteOpenHelper = new AccesorSQLiteOpenHelper(context);
	}

	public static synchronized UserDAOImpl getInstance(Context context) {
		if (instance == null) {
			instance = new UserDAOImpl(context);
		}

		return instance;
	}

	@Override
	public ContentValues savePerson(ContentValues userContentValues) {
		Log.i(TAG, "savePerson()");

		SQLiteDatabase sqLiteDatabase = this.accesorSQLiteOpenHelper
				.getWritableDatabase();
		long rowId = sqLiteDatabase.insertWithOnConflict(
				UserContract.TABLE_NAME, null, userContentValues,
				SQLiteDatabase.CONFLICT_IGNORE);

		return ((rowId != -1L) ? userContentValues : null);
	}

	@Override
	public ContentValues updatePerson(ContentValues userContentValues,
			String whereClause, String[] whereArgs) {
		Log.i(TAG, "UpdatePerson()");

		SQLiteDatabase sqLiteDatabase = accesorSQLiteOpenHelper
				.getWritableDatabase();
		int filasAfectadas = sqLiteDatabase.updateWithOnConflict(
				UserContract.TABLE_NAME, userContentValues, whereClause,
				whereArgs, SQLiteDatabase.CONFLICT_IGNORE);

		return ((filasAfectadas != 0) ? userContentValues : null);
	}

	@Override
	public Integer deletePersons(String whereClause, String[] whereArgs) {
		Log.i(TAG, "deletePerson");

		SQLiteDatabase sqLiteDatabase = accesorSQLiteOpenHelper
				.getWritableDatabase();
		int filasAfectadas = sqLiteDatabase.delete(UserContract.TABLE_NAME,
				whereClause, whereArgs);

		return Integer.valueOf(filasAfectadas);
	}

	@Override
	public List<ContentValues> findUser(String cedula) {
		Log.i(TAG, "findPerson()");

		SQLiteDatabase sqLiteDatabase = this.accesorSQLiteOpenHelper
				.getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery(String.format(
				"SELECT * FROM  %s WHERE %s = ?",
				UserContract.TABLE_NAME, UserContract.Colum.CEDULA),
				new String[] { cedula });
		List<ContentValues> contentValues = cursorToContentValue(cursor, null);
		
		cursor.close();

		return contentValues;
	}

	private List<ContentValues> cursorToContentValue(Cursor cursor,
			String[] columns) {
		List<ContentValues> contentValuesList = new ArrayList<ContentValues>();

		if ((cursor == null) || (cursor.isClosed())) {

			return (contentValuesList);
		}

		if (columns == null) {
			columns = UserContract.Colum.getAllColumns();
		}

		ContentValues contentValues = null;

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			contentValues = new ContentValues();

			for (String column : columns) {
				contentValues.put(column,
						cursor.getString(cursor.getColumnIndex(column)));
			}
			contentValuesList.add(contentValues);
			cursor.moveToNext();
		}

		return (contentValuesList);

	}
}
