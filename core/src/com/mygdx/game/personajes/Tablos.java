package com.mygdx.game.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.LogicaJuego;
import com.mygdx.game.ataques.Kizazo;
import com.mygdx.movimiento.*;

public class Tablos extends Personaje{
    
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private boolean herido = false; 
    private Rectangle hitbox;
    
    private MovimientoStrategy move = new MovimientoIA(900);

    
    public Tablos(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	super(10000, 1);
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txBala = txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	spr.setOriginCenter();
    	spr.setSize(90, 150);
    	hitbox = new Rectangle(x, y, 45, 75);
    	
    }
    
    public void draw(SpriteBatch batch, LogicaJuego logicaJuego){
        float x =  spr.getX();
        float y =  spr.getY();

        if (!herido) {
            
	        // mover
            move.mover(spr, hitbox, 900, 800);
            
	                 
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
    protected boolean puedeTransformarse()
    {
    	return vida < 5000;
    }
    
    @Override
    protected void aplicarTransformacion() {
        if (fase == 2) {
            poder *= 2;
        }
    }
    
    public boolean checkCollision(Kizazo b, int dano) {
    	if(!herido && b.getArea().overlaps(hitbox)){
        	
            //actualizar vidas y herir
            herir(dano);
            
            return true;
        }
        return false;
    }

    public boolean perdio()
    {
    	return estadoPartida();
	}
    
}
      


