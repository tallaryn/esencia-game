package com.mygdx.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EsenciaGame;
import com.mygdx.game.LogicaJuego;
import com.mygdx.game.HiScoreSingleton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PantallaJuego implements Screen {

	private EsenciaGame game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic;
	private LogicaJuego juego;
	private TextureRegion bg;

	public PantallaJuego(EsenciaGame game, int score) 
	{
		this.game = game;		
		juego = new LogicaJuego(score);
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 1200, 800);
		
		
		//inicializar assets; imgen de fondo, musica de fondo y efectos de sonido
		
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.02f);
		
		bg = new TextureRegion(new Texture(Gdx.files.internal("bg_field.png")), 0, 0, 900, 800);
		
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Bring-Me-To-Life.ogg"));
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		gameMusic.play();
	}
		
    
	public void dibujaEncabezado() 
	{
		game.getFont().getData().setScale(1.5f); 

	   
	    float xDerecha = 1200 - 300; 
	    
	    
	    float yInicial = 800 - 40;
	    
	   
	    float espaciado = 35; 

	    
	    game.getFont().draw(batch, "Vidas Goku: " + juego.getGoku().getVida(), xDerecha, yInicial);
	    game.getFont().draw(batch, "Vidas Tablos: " + juego.getTablos().getVida(), xDerecha, yInicial - espaciado);
	    game.getFont().draw(batch, "Score: " + juego.getScore(), xDerecha, yInicial - (espaciado * 2));
	    game.getFont().draw(batch, "HighScore: " + HiScoreSingleton.getInstance().getHiScore(), xDerecha, yInicial - (espaciado * 3));
	    game.getFont().draw(batch, "Poder: " + juego.getGoku().getPoder(), xDerecha, yInicial - (espaciado * 4));
	    game.getFont().draw(batch, "Ki acumulado: " + juego.getGoku().getKi(), xDerecha, yInicial - (espaciado * 5));
	    game.getFont().draw(batch, "	X para acumular: ", xDerecha, yInicial - (espaciado * 6));
		
	}
	
	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

          juego.actualizar();

          batch.begin();
          batch.draw(bg,0,0,1200,800);
          juego.dibujar(batch);
          dibujaEncabezado();
          batch.end();
	
          
          if (juego.haTerminado()) {
        	  	Screen ss = new PantallaGameOver(game, juego.getScore());
				ss.resize(1200, 800);
				game.setScreen(ss);
				dispose();
          }
          
          if(juego.ganaste())
          {
        	  Screen ss = new PantallaFinalBueno(game, juego.getScore());
				ss.resize(1200, 800);
				game.setScreen(ss);
				dispose();
          }

	}
	
    
	
    
	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}
   
}
