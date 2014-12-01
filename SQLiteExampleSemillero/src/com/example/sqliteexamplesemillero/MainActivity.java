package com.example.sqliteexamplesemillero;

import com.example.sqliteexamplesemillero.entidades.User;
import com.example.sqliteexamplesemillero.process.impl.UserProcessImpl;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private UserProcessImpl userProcessImpl;
	private User user;
	private EditText name;
	private EditText id;
	private EditText age;
	private EditText tel;
	private Button save;
	private Button update;
	private Button delete;

	private String nombre;
	private String cedula;
	private String edad;
	private String telefono;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComponents();
	}

	public void initComponents() {
		this.userProcessImpl = new UserProcessImpl(this.getApplicationContext());
		this.name = (EditText) super.findViewById(R.id.nombre_edittext);
		this.id = (EditText) super.findViewById(R.id.id_edittext);
		this.age = (EditText) super.findViewById(R.id.edad_edittext);
		this.tel = (EditText) super.findViewById(R.id.tel_edittext);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onSaveClick(View view) {
		nombre = name.getText().toString();
		cedula = id.getText().toString();
		edad = age.getText().toString();
		telefono = tel.getText().toString();

		user = new User(cedula, nombre, edad, telefono);

		this.userProcessImpl.savePerson(user);

		Toast.makeText(this.getApplicationContext(), "Guardado con éxito",
				Toast.LENGTH_LONG).show();

	}

	public void onDeleteClick(View view) {
		
		cedula = id.getText().toString();
		User userAux = this.userProcessImpl.findPerson(cedula);

		int result = this.userProcessImpl.deletePerson(userAux);

		if (result != 0) {
			Toast.makeText(this.getApplicationContext(), "Eliminado con exito",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this.getApplicationContext(), "Error al eliminar",
					Toast.LENGTH_LONG).show();
		}
	}

	public void onUpdateClick(View view) {
		// nombre = name.getText().toString();
		// //cedula = id.getText().toString();
		// edad = age.getText().toString();
		// telefono = tel.getText().toString();
		//

		cedula = id.getText().toString();
		User userAux = this.userProcessImpl.findPerson(cedula);
		
		userAux.setNombre(name.getText().toString());
		userAux.setEdad(age.getText().toString());
		userAux.setTel(tel.getText().toString());

		this.userProcessImpl.updatePerson(userAux);

		Toast.makeText(this.getApplicationContext(),
				"Actualizado correctametne", Toast.LENGTH_LONG).show();

	}
}
