package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ataques.Kizazo;
import com.mygdx.game.personajes.Goku;
import com.mygdx.game.personajes.Tablos;

public class LogicaJuego {
	private int score;
	private Goku goku;
	private Tablos tablos;
	private  ArrayList<Kizazo> balas = new ArrayList<>();

	
	public LogicaJuego(int score) {
		
		
		this.score = score;
		
		
		// cargar assets de goku   
	    goku = new Goku(Gdx.graphics.getWidth()/2-50,30,
	    		Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
	    		Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
	    		new Texture(Gdx.files.internal("Rocket2.png")),
	    		new Texture(Gdx.files.internal("goku.png"))
	    		); 
	    
	    //cargar assets de tablos
	    tablos = new Tablos(Gdx.graphics.getWidth()/2-50,700,
	    					new Texture(Gdx.files.internal("tablos.png")),
	    					Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
	    					new Texture(Gdx.files.internal("Rocket2.png")),
	    					Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"))
	    					);
	    
	}
	
	public void actualizar()
	{
		// actualizacion colisiones, balas y asteroides
		
		actualizarBalas();

		colisionGoku();
		
		colisionTablos();
		
	}
	
	
	
	public void actualizarBalas()
	{
		for (int i = 0; i < balas.size(); i++) {
            Kizazo b = balas.get(i);
            b.update();
            
            if (b.isDestroyed()) {
                balas.remove(b);
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

	    
	    //Dibujar goku 
	    goku.draw(batch, this);
	    
	    //dibujar tablos
	    tablos.draw(batch, this);
	    
	}
	
	
	public boolean colisionGoku() 
	{
		return false;
	}
	
	public boolean colisionTablos()
	{
		for(int i = 0; i < balas.size(); i++)
		{
			Kizazo k = balas.get(i);
			
			if(tablos.checkCollision(k, goku.getPoder()))
			{
				balas.remove(i);
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
	
	public boolean agregarBala(Kizazo bb) {
    	return balas.add(bb);
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