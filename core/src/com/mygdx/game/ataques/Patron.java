package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public abstract class Patron implements AtaqueStrategy {

    protected Texture txKi;
    protected int cantidad;
    protected float velocidad;

    public Patron(Texture tx, int cantidad, float velocidad) {
        this.txKi = tx;
        this.cantidad = cantidad;
        this.velocidad = velocidad;
    }

    @Override
    public Array<Kizazo> ejecutarAtaque(float x, float y) {
        AtaqueBuilder builder = new AtaqueBuilder();

        for (int i = 0; i < cantidad; i++) {
            float velX = calcularVelocidadX(i);
            float velY = calcularVelocidadY(i); 
            
            builder.agregarKizazo(x, y, velX, velY, txKi);
        }

        return builder.construir();
    }

    public abstract float calcularVelocidadX(int indiceBala);
    public abstract float calcularVelocidadY(int indiceBala);


}
