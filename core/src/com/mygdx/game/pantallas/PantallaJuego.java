package com.mygdx.game.pantallas;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EsenciaGame;
import com.mygdx.game.LogicaJuego;
import com.mygdx.game.ataques.Ball;
import com.mygdx.game.ataques.Kizazo;


public class PantallaJuego implements Screen {

	private EsenciaGame game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic;
	private LogicaJuego juego;

	public PantallaJuego(EsenciaGame game, int ronda, int score,  
						 int velXAsteroides, int velYAsteroides, 
						 int cantAsteroides) 
	{
		this.game = game;		
		juego = new LogicaJuego(ronda, score,  cantAsteroides,
				 				velXAsteroides, velYAsteroides);
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 1200, 800);
		
		
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,1f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Bring-Me-To-Life.ogg")); //
		
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.3f);
		gameMusic.play();
	}
		
    
	public void dibujaEncabezado() 
	{
		CharSequence str = "Vidas: " + juego.getGoku().getVida()+
						   " Ronda: "+juego.getRonda();
		
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		
		game.getFont().draw(batch, "Score:"+juego.getScore(), Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), 
							Gdx.graphics.getWidth()/2-100, 30);
	}
	
	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

          juego.actualizar();

          batch.begin();
          juego.dibujar(batch);
          dibujaEncabezado();
          batch.end();
	
          int puntosFinales = juego.haTerminado();
          if (puntosFinales != 0) {
        	  	Screen ss = new PantallaGameOver(game);
        	  	game.actualizaHighScore(puntosFinales);
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
