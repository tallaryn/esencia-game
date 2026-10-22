package com.mygdx.movimiento;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class MovimientoTeclado implements MovimientoStrategy{

	private float velocidad = 300f;
	
	public MovimientoTeclado() {
		
	}


	@Override
	public void mover(Sprite spr, Rectangle hitbox, float limiteAncho, float limiteAlto) {
		float x = spr.getX();
        float y = spr.getY();
        float paso = velocidad * Gdx.graphics.getDeltaTime();
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= paso;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += paso;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  y -= paso;
        if (Gdx.input.isKeyPressed(Input.Keys.UP))    y += paso	;
        
        // que se mantenga dentro de los bordes de la ventana
        if (x < 0) x = 0;
        if (x + spr.getWidth() > 900) {
            x = 900 - spr.getWidth();
        }

        if (y < 0) y = 0;
        if (y + spr.getHeight() > 800) {
            y = 800 - spr.getHeight();
        }
        
        spr.setPosition(x, y); 
        hitbox.setPosition(x + 15, y + 20);

		
	}

}
