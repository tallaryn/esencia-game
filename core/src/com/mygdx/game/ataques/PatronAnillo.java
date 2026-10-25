package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;

public class PatronAnillo extends Patron {

    public PatronAnillo(Texture tx, int cantidad, float velocidad, int filas) {
        super(tx, cantidad, velocidad, filas);
    }
    
    private float generarAngulo(int i) {
        float anguloCentro = 90.0f;
        float apertura = 360.0f;

        if (cantidad <= 1) {
            return anguloCentro;
        }

        float proporcion = (float) i / (cantidad - 1);

        return anguloCentro - apertura / 2.0f
                + proporcion * apertura;
    }

    @Override
    public float calcularVelocidadX(int i) {
        return velocidad * (float) Math.cos(
                Math.toRadians(generarAngulo(i))
        );
    }

    @Override
    public float calcularVelocidadY(int i) {
        return velocidad * (float) Math.sin(
                Math.toRadians(generarAngulo(i))
        );
    }
}

