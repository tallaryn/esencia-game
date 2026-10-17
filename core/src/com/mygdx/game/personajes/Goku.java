package com.mygdx.game.personajes;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.ataques.Ball;
import com.mygdx.game.ataques.Kizazo;
import com.mygdx.game.LogicaJuego;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;



public class Goku extends Personaje{
	
    private float xVel = 0;
    private float yVel = 0;
    
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private Rectangle hitbox;
    
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    
    private float tiempoUltimoDisparo = 0;
    private final float COOLDOWN = 0.25f; 
    
    private int kiAcumulado = 0;
    private int maxKi = 120;
    
    
    
    
    public Goku(int x, int y, Sound her, Sound bala, Texture txbala, Texture txgoku) 
    {
    	super(3, 20);
    	sonidoHerido = her;
    	this.soundBala = bala;
    	this.txBala = txbala; 
    	spr = new Sprite(txgoku);
    	spr.setPosition(x, y);
    	spr.setOriginCenter();
    	spr.setSize(90, 150);
    	hitbox = new Rectangle(x, y, 25, 45);
    	
    }
    
    
    public void draw(SpriteBatch batch, LogicaJuego juego){
        float x =  spr.getX();
        float y =  spr.getY();
        
        float velocidad = 300f * Gdx.graphics.getDeltaTime();
        tiempoUltimoDisparo += Gdx.graphics.getDeltaTime();
        
        if (!herido) {
        	
	        // que se mueva con teclado
        	if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= velocidad;
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += velocidad;
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  y -= velocidad;
            if (Gdx.input.isKeyPressed(Input.Keys.UP))    y += velocidad;
            
	        // que se mantenga dentro de los bordes de la ventana
            if (x < 0) x = 0;
            if (x + spr.getWidth() > Gdx.graphics.getWidth()) {
                x = Gdx.graphics.getWidth() - spr.getWidth();
            }

            if (y < 0) y = 0;
            if (y + spr.getHeight() > Gdx.graphics.getHeight()) {
                y = Gdx.graphics.getHeight() - spr.getHeight();
            }
            
	        spr.setPosition(x, y); 
	        hitbox.setPosition(x + 15, y + 20);
         
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
                spr.getX() + spr.getWidth() / 2 - 5,
                spr.getY() + spr.getHeight() - 5,
                0, 3, txBala
            );
            juego.agregarBala(bala);
            soundBala.play();
            
            tiempoUltimoDisparo = 0; // Reiniciamos cooldown
        }
        
        //aumentar ki (energia) de goku
        
        if(Gdx.input.isKeyPressed(Input.Keys.X))
        {
        	aumentarKi();
        }
    }
    
    public void aumentarKi()
    {
    	if(kiAcumulado == maxKi)
    	{
    		transformarse();
    		kiAcumulado = 0;
    	}
    	while(kiAcumulado < maxKi)
    	{
    		kiAcumulado ++;
    	}
    }
    
    @Override 
    public void transformarse()
    {
    	sumaPoder(30);
    }
      
    public boolean checkCollision(Ball b) {
    	if(!herido && b.getArea().overlaps(hitbox)){
    		
        	// rebote
            if (xVel ==0) xVel += b.getXSpeed()/2;
            if (b.getXSpeed() ==0) b.setXSpeed(b.getXSpeed() + (int)xVel/2);
            xVel = - xVel;
            b.setXSpeed(-b.getXSpeed());
            
            if (yVel ==0) yVel += b.getySpeed()/2;
            if (b.getySpeed() ==0) b.setySpeed(b.getySpeed() + (int)yVel/2);
            yVel = - yVel;
            b.setySpeed(- b.getySpeed());
            
        	
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
}
