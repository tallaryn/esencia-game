package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface MovimientoStrategy {
	
    void mover(Sprite spr, float limiteAncho, float limiteAlto, float delta);
	
}
