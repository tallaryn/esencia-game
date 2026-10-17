package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ataques.Ball;
import com.mygdx.game.ataques.Kizazo;
import com.mygdx.game.personajes.Goku;

public class LogicaJuego {
	private int score;
	private int ronda;
	private Goku goku;
	private  ArrayList<Ball> balls1 = new ArrayList<>();
	private  ArrayList<Ball> balls2 = new ArrayList<>();
	private  ArrayList<Kizazo> balas = new ArrayList<>();
	private int cantAsteroides;
	private int velXAsteroides; 
	private int velYAsteroides; 

	
	public LogicaJuego(int ronda, int score, int cantAsteroides,
            		   int velXAsteroides, int velYAsteroides) {
		
		
		this.ronda = ronda;
		this.score = score;
		this.cantAsteroides = cantAsteroides;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		
		
		// cargar assets de goku   
	    goku = new Goku(Gdx.graphics.getWidth()/2-50,30,
	    		Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
	    		Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
	    		new Texture(Gdx.files.internal("Rocket2.png")),
	    		new Texture(Gdx.files.internal("goku.png"))
	    		); 
	    
	    
        //crear asteroides
        Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Ball bb = new Ball(r.nextInt((int)Gdx.graphics.getWidth()),
	  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	  	            new Texture(Gdx.files.internal("aGreyMedium4.png")));	   
	  	    balls1.add(bb);
	  	    balls2.add(bb);
	  	    
	    }
	}
	
	
	public void actualizar()
	{
		// actualizacion colisiones, balas y asteroides
		
		actualizarBalas();
		
		actualizarAsteroides();

		huboColision();
		
	      
	}
	
	
	public boolean nivelCompletado()
	{
		if(balls1.size() == 0)
			return true;
		return false;
	}
	
	public void actualizarBalas()
	{
		for (int i = 0; i < balas.size(); i++) {
            Kizazo b = balas.get(i);
            b.update();
            for (int j = 0; j < balls1.size(); j++) 
            {  
            	
              if (b.checkCollision(balls1.get(j))) 
              {          
            	
            	 balls1.remove(j);
            	 balls2.remove(j);
            	 j--;
            	 score +=10;
            	 
              } 
              
  	        }
            
            if (b.isDestroyed()) {
                balas.remove(b);
                i--; //para no saltarse 1 tras eliminar del arraylist
            }
      }
	}
	
	public void actualizarAsteroides()
	{
		for (Ball ball : balls1) {
	          ball.update();
	    }
	}
	
	public void dibujar(SpriteBatch batch)
	{
		// Dibujar balas
	    for (Kizazo b : balas) {
	        b.draw(batch);
	    }

	    // Dibujar asteroides
	    for (Ball b : balls1) {
	        b.draw(batch);
	    }
	    
	    //Dibujar goku
	    goku.draw(batch, this);
	    
	}
	
	public int aumentaRonda()
	{
		return ronda ++;
	}
	
	public int aumentarAsteroides(int cant)
	{
		return cantAsteroides += cant;
	}
	
	public boolean huboColision() 
	{
		for (int i = 0; i < balls1.size(); i++) {
	        Ball b = balls1.get(i);
	        
	        if (goku.checkCollision(b)) {
	            balls1.remove(i);
	            return true;
	        }
	    }
		return false;

	}
	
	public int haTerminado()
	{
		if(goku.perdiste()) {
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

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public Goku getGoku() {
		return goku;
	}

	public void setGoku(Goku goku) {
		this.goku = goku;
	}
	

		
	}