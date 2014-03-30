package com.example.mislugares;
import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.content.DialogInterface;

public class VistaLugar extends Activity {
    private long id;
    private Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = Lugares.elemento((int) id);
        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = (TextView) findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        TextView direccion = (TextView) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        TextView telefono = (TextView) findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        TextView url = (TextView) findViewById(R.id.url);
        url.setText(lugar.getUrl());
        TextView comentario = (TextView) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                           new Date(lugar.getFecha())));
        TextView hora = (TextView) findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                           new Date(lugar.getFecha())));
        RatingBar valoracion = (RatingBar) findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
            new OnRatingBarChangeListener() {
                @Override public void onRatingChanged(RatingBar ratingBar,
                                                float valor, boolean fromUser) {
                    lugar.setValoracion(valor);
                }
        });
        
        //Ocultar elementos
        if(lugar.getTelefono() == 0){
        	findViewById(R.id.telefono).setVisibility(View.GONE);
        	
        }
        else{
        	telefono.setVisibility(View.VISIBLE);
        	telefono.setText(Integer.toString(lugar.getTelefono()));
        }
        
        if(lugar.getDireccion()== null || lugar.getDireccion()==""){
        	findViewById(R.id.direccion).setVisibility(View.GONE);
        }
        else{
        	direccion.setVisibility(View.VISIBLE);
        	direccion.setText(lugar.getDireccion());
        }
        
        if(lugar.getUrl()== null || lugar.getUrl()==""){
        	findViewById(R.id.url).setVisibility(View.GONE);
        }
        else{
        	url.setVisibility(View.VISIBLE);
        	url.setText(lugar.getUrl());
        }
        
        if(lugar.getComentario()==null || lugar.getComentario()==""){
        	findViewById(R.id.comentario).setVisibility(View.GONE);
        }
        else{
        	comentario.setVisibility(View.VISIBLE);
        	comentario.setText(lugar.getComentario());
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	getMenuInflater().inflate(R.menu.vista_lugar, menu);
    	return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.accion_compartir:
			 return true;
		case R.id.accion_llegar:
			return true;
		case R.id.accion_editar:
			return true;
		case R.id.accion_borrar:
			View view = new View(this);
			lanzarcuadroConfirmacion(view);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
    }
    
    public void lanzarcuadroConfirmacion(View view){
    new AlertDialog.Builder(this).setTitle(R.string.titulocuadroborrar)
    .setMessage(R.string.msjborrarlugar).setPositiveButton(R.string.positivo, new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			Lugares.borrar((int)id);
			finish();
			
		}
	}).setNegativeButton(R.string.negativo, new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			dialog.cancel();
			
		}
	}).show();
    }
    
}
