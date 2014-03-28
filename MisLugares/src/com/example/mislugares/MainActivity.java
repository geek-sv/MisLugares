package com.example.mislugares;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.acercaDe:
			lanzarAcercaDe(null);
			break;
		case R.id.config:
			lanzarPreferencias(null);
			break;
		}
		return true;
	}
	
	public void lanzarAcercaDe(View view){
		Intent i= new Intent(this,AcercaDe.class);
		startActivity(i);
	}
	
	public void lanzarPreferencias (View view){
		Intent i = new Intent(this, Preferencias.class);
		startActivity(i);
	}
	
	public void mostrarPreferencias(View view){
		SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(this);
		String s="notificaciones: "+pref.getBoolean("notificaciones", true)+",distancia mínima: "
				+pref.getString("distancia", "?");
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
	
	public void lanzarVistaLugar(View view){
		Intent i = new Intent(this,VistaLugar.class);
		i.putExtra("id",(long)0);
		startActivity(i);
	}
	public void salir(View view){
		finish();
	}

}
