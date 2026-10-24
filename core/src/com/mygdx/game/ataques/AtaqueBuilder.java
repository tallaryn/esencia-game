package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class AtaqueBuilder {

    private Array<Kizazo> proyectiles = new Array<>();

    public AtaqueBuilder agregarKizazo(
            float x, float y, float velX, float velY, Texture tx) {

        proyectiles.add(new Kizazo(x, y, velX, velY, tx));
        return this;
    }
    
    public AtaqueBuilder agregarKizazo( Kizazo at ) {

        proyectiles.add(at);
        return this;
    }


    public Array<Kizazo> construir() {
        Array<Kizazo> resultado = proyectiles;

        proyectiles = new Array<>();

        return resultado;
    }


}