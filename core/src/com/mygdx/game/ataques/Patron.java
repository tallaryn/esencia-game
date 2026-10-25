package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Patron implements AtaqueStrategy {

    private Texture txKi;
    private int cantidad;
    private float velocidad;
    private int filas;
    private float anguloCentro; //direccion de disparo: 90 abajo
    private float apertura; //separacion entre balas

    public Patron(Texture tx, int cantidad, float velocidad, int filas, float anguloCentro, float apertura) {
        this.txKi = tx;
        this.cantidad = cantidad;
        this.velocidad = velocidad;
        this.filas = filas;
        this.anguloCentro = anguloCentro;
        this.apertura = apertura;
    }
    
    public Patron(Texture txBala, float velocidad2) {
    	this.txKi = txBala;
        this.cantidad = 1;
        this.velocidad = velocidad2;
        this.filas = 1;
        this.anguloCentro = 90f;
        this.apertura = 0f;

	}

	@Override
    public Array<Kizazo> ejecutarAtaque(float x, float y) {
        AtaqueBuilder builder = new AtaqueBuilder();
        		
        for (int i = 0; i < filas ; i++)
        {     	
        	float delay = i * 0.36f;
	        for (int j = 0; j < cantidad; j++) {
	        	
	            float velX = calcularVelocidadX(j);
	            float velY = calcularVelocidadY(j); 
	            
	            builder.agregarKizazo(x, y, velX, velY, txKi, delay);
	        }
        }

        return builder.construir();
    }
    
    private float generarAngulo(int i) {
        
        if (cantidad <= 1) {
            return anguloCentro;
        }
        
        float proporcion = (float) i / (cantidad - 1);

        return anguloCentro - apertura / 2.0f
                + proporcion * apertura;
    }


    public float calcularVelocidadX(int i) {
        return velocidad * (float) Math.cos(
                Math.toRadians(generarAngulo(i))
        );
    }

    public float calcularVelocidadY(int i) {
        return velocidad * (float) Math.sin(
                Math.toRadians(generarAngulo(i))
        );
    }

}
