package com.mygdx.game.personajes;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.ataques.Kizazo;
import com.mygdx.game.ataques.Patron;
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
	
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private Rectangle hitbox;
    private Sprite auraKi;
    
    private boolean herido = false;
    private int tiempoHeridoMax=70;
    private int tiempoHerido;
    
    private float tiempoUltimoDisparo = 0;
    private final float COOLDOWN = 0.25f; 
    
    private int kiAcumulado = 0;
    private int maxKi = 500;
    private Patron ataque;
    
    private MovimientoStrategy move = new MovimientoTeclado();
    
    
    
    
    public Goku(int x, int y, Sound her, Sound bala, Texture txbala, Texture txgoku, Texture txAura) 
    {
    	super(3, 20);
    	sonidoHerido = her;
    	this.soundBala = bala;
    	this.txBala = txbala; 
    	spr = new Sprite(txgoku);
    	spr.setPosition(x, y);
    	spr.setOriginCenter();
    	spr.setSize(90, 150);
    	hitbox = new Rectangle(x, y, 15, 15);
    	
    	auraKi = new Sprite(txAura);
    	auraKi.setSize(100, 180);
    	ataque = new Patron(txBala, 3f);
    	
    }
    
    
    public void draw(SpriteBatch batch, LogicaJuego juego){
        float x =  spr.getX();
        float y =  spr.getY();

        tiempoUltimoDisparo += Gdx.graphics.getDeltaTime();
        
        if (!herido) {
        	
        	//aumentar ki de goku -> no puede moverse mientras lo hace
        	if(!aumentarKi(batch)) {
        		
        		// que se mueva con teclado
        		move.mover(spr, hitbox, 900, 800);
        		
        		 // disparo
                
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && tiempoUltimoDisparo >= COOLDOWN) {         
                    
                	juego.agregarBala(ataque.ejecutarAtaque(spr.getX() + spr.getWidth() / 2 - 5,
                						  spr.getY() + spr.getHeight() - 5
                						  )
                					 );
                    soundBala.play();
                    
                    tiempoUltimoDisparo = 0; // Reiniciamos cooldown
                }
        	}

 		    spr.draw(batch);
        } else {
           spr.setX(spr.getX()+MathUtils.random(-2,2));
 		   spr.draw(batch); 
 		  spr.setX(x);
 		   tiempoHerido--;
 		   if (tiempoHerido<=0) herido = false;
 		 }
       
        
        
    }
    
    public boolean aumentarKi(SpriteBatch batch)
    {
    	if(fase == maxFase) return false;
    	if(Gdx.input.isKeyPressed(Input.Keys.X))
    	{
    		auraKi.setPosition(spr.getX()-5 , spr.getY());
    		auraKi.draw(batch);
    		TRANSFORMARSE();
	    	
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
    	float posX = spr.getX();
    	float posY = spr.getY();
    	spr = new Sprite(new Texture (Gdx.files.internal("goku-ssj.png")));
    	spr.setSize(90, 150);
    	spr.setPosition(posX, posY);
    	
    	Sound soundSSJ = Gdx.audio.newSound(Gdx.files.internal("ssjTransformacion.ogg"));
    	soundSSJ.play();
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
