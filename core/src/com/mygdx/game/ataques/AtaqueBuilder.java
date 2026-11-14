package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class AtaqueBuilder {
    private Texture textura;
    private float origenX;
    private float origenY;
    private int cantidad = 1;
    private float velocidad = 5f;
    private int filas = 1;
    private float anguloCentro = 90f;
    private float apertura = 0f;

    public AtaqueBuilder conTextura(Texture textura) {
        this.textura = textura;
        return this;
    }

    public AtaqueBuilder desdePosicion(float x, float y) {
        this.origenX = x;
        this.origenY = y;
        return this;
    }

    public AtaqueBuilder conCantidad(int cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public AtaqueBuilder conVelocidad(float velocidad) {
        this.velocidad = velocidad;
        return this;
    }

    public AtaqueBuilder conFilas(int filas) {
        this.filas = filas;
        return this;
    }

    public AtaqueBuilder conAngulo(float anguloCentro, float apertura) {
        this.anguloCentro = anguloCentro;
        this.apertura = apertura;
        return this;
    }

    public Array<Kizazo> construir() {
        Array<Kizazo> proyectiles = new Array<>();
        for (int i = 0; i < filas; i++) {
            float delay = i * 0.36f;
            for (int j = 0; j < cantidad; j++) {
                float angulo = generarAngulo(j);
                float velX = velocidad * (float) Math.cos(Math.toRadians(angulo));
                float velY = velocidad * (float) Math.sin(Math.toRadians(angulo));
                proyectiles.add(new Kizazo(origenX, origenY, velX, velY, textura, delay));
            }
        }
        return proyectiles;
    }

    private float generarAngulo(int indice) {
        if (cantidad <= 1) return anguloCentro;
        float proporcion = (float) indice / (cantidad - 1);
        return anguloCentro - (apertura / 2.0f) + proporcion * apertura;
    }
}