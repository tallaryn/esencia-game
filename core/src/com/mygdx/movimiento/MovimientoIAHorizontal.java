package com.mygdx.movimiento;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class MovimientoIA implements MovimientoStrategy {

    private float velocidad = 300f;
    private float[] puntosX;       
    private int indiceActual = 0;  
    
    private boolean esperando = false;     
    private float tiempoEsperando = 0f;     
    private float tiempoEsperaMax = 7.0f;   
    
    public MovimientoIA(float limiteAncho) {
        puntosX = new float[] {
            limiteAncho / 8,
            limiteAncho / 3,
            limiteAncho - 300,
            limiteAncho / 3
        };
    }

    @Override
    public void mover(Sprite spr, Rectangle hitbox, float limiteAncho, float limiteAlto) {
        float delta = Gdx.graphics.getDeltaTime();
        float x = spr.getX();
        float objetivoX = puntosX[indiceActual];
        float objetivoY = limiteAlto - 200; 

        
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
        }

        
        spr.setPosition(x, objetivoY); 
        
       
        float offsetX = (spr.getWidth() - hitbox.getWidth()) / 2f;
        float offsetY = (spr.getHeight() - hitbox.getHeight()) / 2f;
        hitbox.setPosition(x + offsetX, objetivoY + offsetY);
    }
}