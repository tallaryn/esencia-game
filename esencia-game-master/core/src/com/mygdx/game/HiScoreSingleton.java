package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences; //Biblioteca para manejar

public class HiScoreSingleton {
	private static final String PREFS_NAME = "esencia_preferences";
    private static final String KEY_HIGHSCORE = "highscore";
    
    private Preferences prefs;
    private int hiScore; //Puntuacion maxima
    
    private HiScoreSingleton() {
        this.prefs = Gdx.app.getPreferences(PREFS_NAME);
        this.hiScore = prefs.getInteger(KEY_HIGHSCORE, 0);
    }
    
    private static class Holder { //Tipo Singleton Lazy Initialization
    	private static final HiScoreSingleton INSTANCE = new HiScoreSingleton();
    }
    
    public static HiScoreSingleton getInstance() {
    	return Holder.INSTANCE;
    }
    
    public int getHiScore() {
    	return this.hiScore;
    }
    
    public boolean manejarRecord(int finalScore) { //Evalua si el puntaje supera el record. En ese caso, lo guarda.
    	if (finalScore > hiScore) {
    		hiScore = finalScore;
    		prefs.putInteger(KEY_HIGHSCORE, hiScore);
    		prefs.flush();
    		return true;
    	}
    	return false;
    }
}