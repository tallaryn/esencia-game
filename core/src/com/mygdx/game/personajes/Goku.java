package com.mygdx.game.personajes;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.ataques.Kizazo;
import com.mygdx.game.LogicaJuego;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.movimiento.*;



public class Goku extends Personaje{
	
    private float xVel = 0;
    private float yVel = 0;
    
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private Rectangle hitbox;
    private Texture auraKi;
    
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    
    private float tiempoUltimoDisparo = 0;
    private final float COOLDOWN = 0.25f; 
    
    private int kiAcumulado = 0;
    private int maxKi = 1000;
    
    private MovimientoStrategy move = new MovimientoTeclado();
    
    
    
    
    public Goku(int x, int y, Sound her, Sound bala, Texture txbala, Texture txgoku, Texture txAura) 
    {
    	super(3, 20);
    	sonidoHerido = her;
    	this.soundBala = bala;
    	this.txBala = txbala; 
    	auraKi = txAura;
    	spr = new Sprite(txgoku);
    	spr.setPosition(x, y);
    	spr.setOriginCenter();
    	spr.setSize(90, 150);
    	hitbox = new Rectangle(x, y, 15, 15);
    	
    }
    
    
    public void draw(SpriteBatch batch, LogicaJuego juego){
        float x =  spr.getX();
        float y =  spr.getY();

        tiempoUltimoDisparo += Gdx.graphics.getDeltaTime();
        
        if (!herido) {
        	
        	//aumentar ki de goku -> no puede moverse mientras lo hace
        	if(!aumentarKi()) {
        		
        		// que se mueva con teclado
        		move.mover(spr, hitbox, 900, 800);
        	}

 		    spr.draw(batch);
        } else {
           spr.setX(spr.getX()+MathUtils.random(-2,2));
 		   spr.draw(batch); 
 		  spr.setX(x);
 		   tiempoHerido--;
 		   if (tiempoHerido<=0) herido = false;
 		 }
        // disparo
        
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && tiempoUltimoDisparo >= COOLDOWN) {         
            Kizazo bala = new Kizazo(
                (float) spr.getX() + spr.getWidth() / 2 - 5,
                (float) spr.getY() + spr.getHeight() - 5,
                0, 3, txBala
            );
            juego.agregarBala(bala);
            soundBala.play();
            
            tiempoUltimoDisparo = 0; // Reiniciamos cooldown
        }
        
        
    }
    
    public boolean aumentarKi()
    {
    	if(fase == maxFase) return false;
    	if(Gdx.input.isKeyPressed(Input.Keys.X))
    	{
	    	transformarse();
	    	kiAcumulado ++;
	    	return true;
    	}
    	return false;
    }
   
    @Override
    protected boolean puedeTransformarse() {
    	return kiAcumulado == maxKi;
    }
    
    @Override 
    protected void aplicarTransformacion()
    {
		//cambiar png de goku super saiyan
		sumaPoder(80);
    }
    
    @Override
    protected void finalTransformacion() {
    	kiAcumulado = 0;
    }
    
    public boolean checkCollision(Kizazo b) {
    	if(!herido && b.getArea().overlaps(hitbox)){            
        	
            //actualizar vidas y herir
            herir(1);
            
            return true;
        }
        return false;
    }
    
    @Override
    public void herir(int vida)
    {
    	quitarVida(vida);
    	herido = true;
	    tiempoHerido=tiempoHeridoMax;
	    sonidoHerido.play();

    }
    
    public boolean perdiste() {
       return estadoPartida();
    }
    public boolean estaHerido() {
 	   return herido;
    }
    
    public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
    public int getKi() {return kiAcumulado;}
}
