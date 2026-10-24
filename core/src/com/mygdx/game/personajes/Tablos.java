package com.mygdx.game.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.LogicaJuego;
import com.mygdx.game.ataques.Kizazo;
import com.mygdx.game.ataques.Patron;
import com.mygdx.game.ataques.PatronAbanico;
import com.mygdx.movimiento.MovimientoIA;
import com.mygdx.movimiento.MovimientoStrategy;

public class Tablos extends Personaje{
    
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txKi;
    private boolean herido = false; 
    private Rectangle hitbox;
    
    private MovimientoStrategy move = new MovimientoIA(900);
    private Patron ataque; 
    private int tipoAtaque = 0;
    private float tiempoCambioFaseAtaque = 0f;
    private int vidaMaxima;

    
    // Control de Cooldown para el disparo
    private float tiempoUltimoDisparo = 0f;
    private float cooldownAtaque = 2.0f;

    
    public Tablos(int x, int y, int vidaMax, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	super(vidaMax, 1);
    	vidaMaxima = vidaMax;
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txKi= txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	spr.setOriginCenter();
    	spr.setSize(90, 150);
    	hitbox = new Rectangle(x, y, 45, 75);
    	ataque = new PatronAbanico(txKi, 5, -4f, 3); 
    	
    }
    
    public void draw(SpriteBatch batch, LogicaJuego logicaJuego){
        float x =  spr.getX();
        float y =  spr.getY();

        
	        // mover
            move.mover(spr, hitbox, 900, 800);
        
            transformarse();
            
            // atacar
            tiempoUltimoDisparo += Gdx.graphics.getDeltaTime();
            if (tiempoUltimoDisparo >= cooldownAtaque) {
	            disparar(logicaJuego, x, y);
	            tiempoUltimoDisparo = 0f;
            }
            
         // rotar ataques
            tiempoCambioFaseAtaque += Gdx.graphics.getDeltaTime();
            if (tiempoCambioFaseAtaque >= 5.0f) {
                tiempoCambioFaseAtaque = 0f;
                rotarAtaque();
            }

	                 
 		    spr.draw(batch);
        
 	
	        
        }
    
    
    private void disparar(LogicaJuego logicaJuego, float x, float y) {

        x += spr.getWidth() / 2f;
        y += spr.getHeight() / 2f;

        Array<Kizazo> nuevos = ataque.ejecutarAtaque(x, y);

        logicaJuego.agregarProyectilesEnemigos(nuevos);
        
    }
    
    private void rotarAtaque() {
//        tipoAtaque = (tipoAtaque + 1) % 3;
    	tipoAtaque = 0;
        if (fase != 2) {
            // ataques fase 1
            switch (tipoAtaque) {
                case 0: ataque = new PatronAbanico(txKi, 5, -4f, 3); break;
                /*
                case 1: ataque = new PatronAnillo(txKi, 12, -3f); break;
                case 2: ataque = new PatronEspiral(txKi, 20, -4f); break;
                */
            }
        } else {
            // ataques fase 2
            switch (tipoAtaque) {
                case 0: 
                	ataque = new PatronAbanico(txKi, 8, -5.5f, 3); 
                	break; // Más denso y rápido
                	
                
                /*
                case 1: ataque = new PatronAnillo(txKi, 36, -5f); break;  // Círculo perfecto cerrado
                case 2: ataque = new PatronEspiral(txKi, 60, -8f); break; // Lluvia masiva
                */
            }
        }
    }
    
    @Override
    public void herir(int dano)
    {
    	quitarVida(dano);
    }
    
    @Override
    protected boolean puedeTransformarse()
    {
    	return (vida < vidaMaxima * 0.5);
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
      


