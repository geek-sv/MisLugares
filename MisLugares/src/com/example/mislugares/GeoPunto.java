package com.example.mislugares;

public class GeoPunto {

	double longitud, latitud;
	
	public GeoPunto(double latitud, double longitud) {
		// TODO Auto-generated constructor stub
		this.latitud=(int) (latitud*1E6);
		this.longitud= (int)(longitud*1E6);
	}
	
	public String toString(){
		return new String ("lat:"+latitud+" long"+longitud);
	}
	
	public double distancia(GeoPunto punto){
		final double RADIO_TIERRA=6378000;//en metros
		double dLat=Math.toRadians(punto.latitud-latitud);
		double dLong=Math.toRadians(punto.longitud-longitud);
		double a = Math.sin(dLat/2)*
					Math.sin(dLat/2)+
					Math.sin(dLong/2)*
					Math.sin(dLong/2)*
					Math.cos(Math.toRadians(punto.latitud))*
					Math.cos(Math.toRadians(latitud));
		double c=2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return RADIO_TIERRA*c;
	}
	
	public double getLatitud(){
		return latitud;
	}
	
	public double getLongitud(){
		return longitud;
	}
}