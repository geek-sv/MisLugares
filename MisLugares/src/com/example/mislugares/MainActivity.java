package com.example.mislugares;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	public BaseAdapter adaptador;
	public MediaPlayer mp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mp = MediaPlayer.create(this,R.raw.audio);
		//adaptador = new ArrayAdapter<String>(this, R.layout.elemento_lista,R.id.nombre,Lugares.listaNombres());
		adaptador= new AdaptadorLugares(this);
		setListAdapter(adaptador);
		
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
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		if(mp!=null){
			int pos=mp.getCurrentPosition();
			outState.putInt("posicion", pos);
		}
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle state) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(state);
		if(state != null && mp !=null){
			int pos= state.getInt("posicion");
			mp.seekTo(pos);
		}
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
	
	/*public void lanzarVistaLugar(View view){
		Intent i = new Intent(this,VistaLugar.class);
		i.putExtra("id",(long)0);
		startActivity(i);
	}*/
	
/*	public void lanzarVistaLugar(View view){
		final EditText entrada = new EditText(this);
		entrada.setText("0");
		new AlertDialog.Builder(this).setTitle("Selección de lugar")
		.setMessage("indica su id:").setView(entrada)
		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				long id= Long.parseLong(entrada.getText().toString());
				Intent i = new Intent(MainActivity.this, VistaLugar.class);
				i.putExtra("id", id);
				startActivity(i);
			}
		}).setNegativeButton("Cancelar", null).show();
			
	}*/
	
	@Override
	protected void onListItemClick(ListView listView,
			View vista, int posicion, long id){
		super.onListItemClick(listView, vista, posicion, id);
		Intent intent= new Intent(this,VistaLugar.class);
		intent.putExtra("id", id);
		startActivity(intent);
	}
	
	public void salir(View view){
		finish();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.stop();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mp.start();
		
	}
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mp.release();
		
	}
	

}
