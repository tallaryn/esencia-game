package com.mygdx.movimiento;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public interface MovimientoStrategy {
	
    public void mover(Sprite spr, Rectangle hitbox, float limiteAncho, float limiteAlto);
	
}
