package com.mygdx.game.pantallas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.EsenciaGame;
import com.mygdx.game.HiScoreSingleton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PantallaMenu implements Screen {

	private EsenciaGame game;
	private OrthographicCamera camera;
	private TextureRegion bg;

	public PantallaMenu(EsenciaGame game) {
		this.game = game;
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
		bg = new TextureRegion(new Texture(Gdx.files.internal("bg_menu.png")), 0, 0, 900, 800);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
		game.getBatch().draw(bg,0,0,1200,800);

		int recordPuntaje = HiScoreSingleton.getInstance().getHiScore();
		game.getFont().draw(game.getBatch(), "Record: " + recordPuntaje, 150, 350);
		
		game.getFont().draw(game.getBatch(), "Presiona cualquier tecla para empezar ...", 150, 250);
	
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