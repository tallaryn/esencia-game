package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Patron implements AtaqueStrategy {

    private Texture txKi;
    private int cantidad;
    private float velocidad;
    private int filas;
    private float anguloCentro;
    private float apertura;

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
        return new AtaqueBuilder()
                .conTextura(txKi)
                .desdePosicion(x, y)
                .conCantidad(cantidad)
                .conVelocidad(velocidad)
                .conFilas(filas)
                .conAngulo(anguloCentro, apertura)
                .construir();
    }
}