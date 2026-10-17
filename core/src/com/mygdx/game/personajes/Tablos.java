package com.mygdx.game.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.pantallas.PantallaJuego;

public class Tablos extends Personaje{
    
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private boolean herido = false; 
    private Rectangle hitbox;
    
    public Tablos(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	super(2000, 1);
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txBala = txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	spr.setOriginCenter();
    	spr.setSize(90, 150);
    	hitbox = new Rectangle(x, y, 45, 75);
    	
    }
    
    public void draw(SpriteBatch batch, PantallaJuego juego){
        float x =  spr.getX();
        float y =  spr.getY();
        
        float velocidad = 300f * Gdx.graphics.getDeltaTime();
        
        if (!herido) {
            
	        // que se mantenga dentro de los bordes de la ventana
            if (x < 0) x = 0;
            if (x + spr.getWidth() > Gdx.graphics.getWidth()) x = Gdx.graphics.getWidth() - spr.getWidth();
            if (y < 0) y = 0;
            if (y + spr.getHeight() > Gdx.graphics.getHeight()) y = Gdx.graphics.getHeight() - spr.getHeight();
            
	        spr.setPosition(x, y); 
	        hitbox.setPosition(x + 15, y + 20);
         
 		    spr.draw(batch);
        } else {
           spr.setX(spr.getX()+MathUtils.random(-2,2));
 		   spr.draw(batch); 
 		  spr.setX(x);
 		 }
        // disparo
        
        
    }
    
    @Override
    public void herir(int dano)
    {
    	quitarVida(dano);
    }
    
    @Override
    public void transformarse()
    {
    	aumentarFase();
    }
}
      


