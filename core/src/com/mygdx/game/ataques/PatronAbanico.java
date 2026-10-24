package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;

public class PatronAbanico extends Patron {

    public PatronAbanico(Texture tx, int cantidad, float velocidad) {
        super(tx, cantidad, velocidad);
    }

    @Override
    public float calcularVelocidadX(int i) {

        return velocidad * (float) Math.cos(generarAngulo(i));
    }

    @Override
    public float calcularVelocidadY(int i) {

        return velocidad * (float) Math.sin(generarAngulo(i));
    }
    
    private float generarAngulo(int i) {
        float anguloCentro = 270.0f; 
        float apertura = 60.0f;      
        
        
        if (cantidad <= 1) return anguloCentro; 
        
        float anguloInicio = anguloCentro - (apertura / 2.0f);
        float pasoAngulo = apertura / (cantidad - 1);
        
        return anguloInicio + (i * pasoAngulo);
    }

}

