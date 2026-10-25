package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public abstract class Patron implements AtaqueStrategy {

    protected Texture txKi;
    protected int cantidad;
    protected float velocidad;
    protected int filas;

    public Patron(Texture tx, int cantidad, float velocidad, int filas) {
        this.txKi = tx;
        this.cantidad = cantidad;
        this.velocidad = velocidad;
        this.filas = filas;
    }

    @Override
    public Array<Kizazo> ejecutarAtaque(float x, float y) {
        AtaqueBuilder builder = new AtaqueBuilder();
        		
        for (int i = 0; i < filas ; i++)
        {     	
        	float delay = i * 0.4f;
	        for (int j = 0; j < cantidad; j++) {
	        	
	            float velX = calcularVelocidadX(j);
	            float velY = calcularVelocidadY(j); 
	            
	            builder.agregarKizazo(x, y, velX, velY, txKi, delay);
	        }
        }

        return builder.construir();
    }

    public abstract float calcularVelocidadX(int indiceBala);
    public abstract float calcularVelocidadY(int indiceBala);


}
