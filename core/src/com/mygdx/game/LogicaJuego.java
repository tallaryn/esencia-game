package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ataques.Kizazo;
import com.mygdx.game.personajes.Goku;
import com.mygdx.game.personajes.Tablos;

public class LogicaJuego {
	private int score;
	private Goku goku;
	private Tablos tablos;
	private  Array<Kizazo> balas = new Array<>();
	private Array<Kizazo> proyectilesEnemigos = new Array<>();

	
	public LogicaJuego(int score) {
		
		
		this.score = score;
		
		
		// cargar assets de goku   
	    goku = new Goku(Gdx.graphics.getWidth()/2-50,30,
	    		Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
	    		Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
	    		new Texture(Gdx.files.internal("Rocket2.png")),
	    		new Texture(Gdx.files.internal("goku.png")),
	    		new Texture(Gdx.files.internal("auraKi.png"))
	    		); 
	    
	    //cargar assets de tablos
	    tablos = new Tablos(Gdx.graphics.getWidth()/2-50,700,
	    					11000,
	    					new Texture(Gdx.files.internal("tablos.png")),
	    					Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
	    					new Texture(Gdx.files.internal("Rocket2.png")),
	    					Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"))
	    					);
	    
	}
	
	public void actualizar()
	{
		// actualizacion colisiones, balas y asteroides
		
		actualizarAtaque(balas);
		
		actualizarAtaque(proyectilesEnemigos);

		colisionGoku();
		
		colisionTablos();
		
	}
	
	
	
	public void actualizarAtaque(Array<Kizazo> ataque)
	{
		for (int i = 0; i < ataque.size; i++) {
            Kizazo b = ataque.get(i);
            b.update();
            
            if (b.isDestroyed()) {
                ataque.removeIndex(i);
                i--; //para no saltarse 1 tras eliminar del arraylist
            }
      }
	}
	
	
	public void dibujar(SpriteBatch batch)
	{
		// Dibujar balas
	    for (Kizazo b : balas) {
	        b.draw(batch);
	    }
	    
	    for(Kizazo p : proyectilesEnemigos)
	    {
	    	p.draw(batch);
	    }
	    
	    //Dibujar goku 
	    goku.draw(batch, this);
	    
	    //dibujar tablos
	    tablos.draw(batch, this);
	    
	}
	
	
	public boolean colisionGoku() 
	{
		for(int i = 0; i < proyectilesEnemigos.size ; i++)
		{
			
			Kizazo k = proyectilesEnemigos.get(i);
			
			if(goku.checkCollision(k))
			{
				proyectilesEnemigos.removeIndex(i);
				return true;
			}
		}

		return false;
	}
	
	public boolean colisionTablos()
	{
		for(int i = 0; i < balas.size; i++)
		{
			Kizazo k = balas.get(i);
			
			if(tablos.checkCollision(k, goku.getPoder()))
			{
				balas.removeIndex(i);
				score += 30;
				return true;
			}
		}
		return false;
	}
	
	public int haTerminado()
	{
		if(goku.perdiste()) {
			HiScoreSingleton.getInstance().manejarRecord(this.score);
			return score;
		}
		return 0;
	}
	
	public int ganaste()
	{
		if(tablos.perdio())
		{
			HiScoreSingleton.getInstance().manejarRecord(this.score);
			return score;
		}
		return 0;
	}
	
	public void agregarBala(Array<Kizazo> bb) {
    	balas.addAll(bb);
    }
	
	public void agregarProyectilesEnemigos(Array<Kizazo> bb) {
    	proyectilesEnemigos.addAll(bb);
    }
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public Goku getGoku() {
		return goku;
	}
	
	public Tablos getTablos()
	{
		return tablos;
	}

	public void setGoku(Goku goku) {
		this.goku = goku;
	}
	
	public void setTablos(Tablos tablos) {
		this.tablos = tablos;
	}
}