package com.example.mislugares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AdaptadorLugares extends BaseAdapter {
	private LayoutInflater inflador;// Crea layout a partir de xml
	TextView nombre, direccion;
	ImageView foto;
	RatingBar valoracion;

	public AdaptadorLugares(Context contexto){
		inflador=(LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
	}
	
	public View getView(int posicion, View vistaReciclada, ViewGroup padre){
		Lugar lugar=Lugares.elemento(posicion);
		if(vistaReciclada==null){
			vistaReciclada=inflador.inflate(R.layout.elemento_lista, null);
		}
		nombre=(TextView) vistaReciclada.findViewById(R.id.nombre);
		direccion=(TextView) vistaReciclada.findViewById(R.id.direccion);
		foto=(ImageView) vistaReciclada.findViewById(R.id.foto);
		valoracion= (RatingBar) vistaReciclada.findViewById(R.id.valoracion);
		nombre.setText(lugar.getNombre());
		direccion.setText(lugar.getDireccion());
		int id=R.drawable.otros;
		switch(lugar.getTipo()){
		case RESTAURANTE:id=R.drawable.restaurante; break;
		case BAR:		 id=R.drawable.bar; break;
		case COPAS:		 id=R.drawable.copas; break;
		case ESPECTACULO:id=R.drawable.espectaculos; break;
		case HOTEL:      id=R.drawable.hotel; break;
		case COMPRAS:    id=R.drawable.compras; break;
		case EDUCACION:  id=R.drawable.educacion; break;
		case DEPORTE:    id=R.drawable.deporte; break;
		case NATURALEZA: id=R.drawable.naturaleza; break;
		case GASOLINERA: id=R.drawable.gasolinera; break;
		}
		foto.setImageResource(id);
		foto.setScaleType(ImageView.ScaleType.FIT_END);
		valoracion.setRating(lugar.getValoracion());
		return vistaReciclada;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Lugares.size();
	}
	@Override
	public Object getItem(int posicion) {
		// TODO Auto-generated method stub
		return Lugares.elemento(posicion);
	}
	@Override
	public long getItemId(int posicion) {
		// TODO Auto-generated method stub
		return posicion;
	}
	
}
