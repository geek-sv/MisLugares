package com.example.mislugares;



import android.app.Activity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.Spinner;


public class EdicionLugar extends Activity{
	
	
	private long id;
    private Lugar lugar;
    private EditText nombre;
    private Spinner tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;
    
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.edicion_lugar);
	        Bundle extras = getIntent().getExtras();
	        id = extras.getLong("id", -1);
	        lugar = Lugares.elemento((int) id);
	        nombre = (EditText) findViewById(R.id.nombre);
	        nombre.setText(lugar.getNombre());
	        //ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
	        //logo_tipo.setImageResource(lugar.getTipo().getRecurso());
	         
	        tipo = (Spinner) findViewById(R.id.tipo);
	        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,TipoLugar.getNombres());
	        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        tipo.setAdapter(adaptador);
	        tipo.setSelection(lugar.getTipo().ordinal());
	        
	        
	        direccion = (EditText) findViewById(R.id.direccion);
	        direccion.setText(lugar.getDireccion());
	        telefono = (EditText) findViewById(R.id.telefono);
	        telefono.setText(Integer.toString(lugar.getTelefono()));
	         url = (EditText) findViewById(R.id.url);
	        url.setText(lugar.getUrl());
	         comentario = (EditText) findViewById(R.id.comentario);
	        comentario.setText(lugar.getComentario());
	
	    }
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	    	// TODO Auto-generated method stub
	    	getMenuInflater().inflate(R.menu.edicion_lugar, menu);
	    	return true;
	    }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
			case R.id.accion_cancelaredicion:
				 return true;
			case R.id.accion_guardaredicion:
				lugar.setNombre(nombre.getText().toString());
				lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
				lugar.setDireccion(direccion.getText().toString());
				lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
				lugar.setUrl(url.getText().toString());
				lugar.setComentario(comentario.getText().toString());
				finish();
				
				//return true;
			
			default:
				return super.onOptionsItemSelected(item);

			}
	    }

}
