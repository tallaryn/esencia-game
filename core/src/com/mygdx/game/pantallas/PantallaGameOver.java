package com.mygdx.game.pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.EsenciaGame;
import com.mygdx.game.HiScoreSingleton;

public class PantallaGameOver implements Screen {

	private EsenciaGame game;
	private OrthographicCamera camera;
	
	private int puntajeObtenido;
	private boolean esNuevoRecord;

	public PantallaGameOver(EsenciaGame game, int puntajeFinal) {
		this.game = game;
		this.puntajeObtenido = puntajeFinal;
		
		if(puntajeFinal == HiScoreSingleton.getInstance().getHiScore()) { //Si el score obtenido coincide con el record, actulizar
			this.esNuevoRecord = true;
		}
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
		game.getFont().draw(game.getBatch(), "Game Over !!! ", 120, 400,400,1,true);
		game.getFont().draw(game.getBatch(), "Score: " + puntajeObtenido, 100, 350);
		int max = HiScoreSingleton.getInstance().getHiScore();
		game.getFont().draw(game.getBatch(), "High Score: " + max, 100, 300);
		
		if (esNuevoRecord) {
			game.getFont().draw(game.getBatch(), "NUEVO RECORD !!! ", 100, 250);
		}
		game.getFont().draw(game.getBatch(), "Presiona cualquier tecla para reiniciar ...", 100, 200);
	
		game.getBatch().end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Screen ss = new PantallaJuego(game,0);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}
	}
 
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		
	}
   
}