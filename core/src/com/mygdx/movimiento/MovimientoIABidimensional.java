package com.mygdx.movimiento;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class MovimientoIABidimensional implements MovimientoStrategy {

	private float velocidad = 300f;
    private float[] puntosX;
    private float [] puntosY;
    private int indiceActual = 0;  
    
    private boolean esperando = false;     
    private float tiempoEsperando = 0f;     
    private float tiempoEsperaMax = 7.0f;   

	public MovimientoIABidimensional(float limiteAncho, float limiteAlto) {
        puntosX = new float[] {
    		limiteAncho / 8,
            limiteAncho / 3,
            limiteAncho - 300,
            limiteAncho / 3
        };
        puntosY = new float[] {
    		limiteAlto-200,
            limiteAlto-300,
            limiteAlto-200,
            limiteAlto-300,
            limiteAlto-200
        };
        
        tiempoEsperaMax = 5.5f;
    }


	@Override
	public void mover(Sprite spr, Rectangle hitbox, float limiteAncho, float limiteAlto) {
        float delta = Gdx.graphics.getDeltaTime();
        float x = spr.getX();
        float y = spr.getY();
        float objetivoX = puntosX[indiceActual];
        float objetivoY = puntosY[indiceActual % puntosY.length];

        
        if (esperando) {
            tiempoEsperando += delta; 
            
            if (tiempoEsperando >= tiempoEsperaMax) {
                tiempoEsperando = 0f;         
                esperando = false;            
                indiceActual = (indiceActual + 1) % puntosX.length; 
            }
        } 
        else {
            
            float paso = velocidad * delta;

            if (Math.abs(x - objetivoX) < paso) {
               
                x = objetivoX;
                esperando = true; 
            } 
            else if (x < objetivoX) {
                x += paso;
            } 
            else {
                x -= paso;
            }
            
            if(Math.abs(y - objetivoY) < paso)
            {
            	y = objetivoY;
            }
            else if(y < objetivoY)
            {
            	y += paso;
            }
            else
            {
            	y -= paso;
            }
        }

        
        spr.setPosition(x, y); 
        
       
        float offsetX = (spr.getWidth() - hitbox.getWidth()) / 2f;
        float offsetY = (spr.getHeight() - hitbox.getHeight()) / 2f;
        hitbox.setPosition(x + offsetX, y + offsetY);
    }

}


