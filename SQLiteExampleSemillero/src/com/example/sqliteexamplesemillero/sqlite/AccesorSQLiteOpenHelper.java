package com.example.sqliteexamplesemillero.sqlite;

import com.example.sqliteexamplesemillero.sqlite.contract.PersistanceContract;
import com.example.sqliteexamplesemillero.sqlite.contract.UserContract;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AccesorSQLiteOpenHelper extends SQLiteOpenHelper {

	private static String TAG = AccesorSQLiteOpenHelper.class.getSimpleName();

	public AccesorSQLiteOpenHelper(Context context) {
		super(context, PersistanceContract.DATABASE_NAME, null,
				PersistanceContract.DATABASE_VERSION);

		Log.d(TAG, "Se creo la Base de datos");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String userSqlCreator = String
				.format("CREATE TABLE %s ( %s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)",
						UserContract.TABLE_NAME, UserContract.Colum.CEDULA,
						UserContract.Colum.NOMBRE, UserContract.Colum.EDAD,
						UserContract.Colum.TELEFONO);

		Log.d(TAG, userSqlCreator);
		db.execSQL(userSqlCreator);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(String.format("DROP TABLE IF EXISTS %s",
				UserContract.TABLE_NAME));

		this.onCreate(db);

	}
}
