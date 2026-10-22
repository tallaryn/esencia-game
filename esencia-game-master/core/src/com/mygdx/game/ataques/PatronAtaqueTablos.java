package com.mygdx.game.ataques;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class PatronAtaqueTablos {
	
		private Texture textura;

	    public PatronAtaqueTablos(Texture textura) {
	        this.textura = textura;
	    }

	    public void ejecutar(float x, float y,
	                         Array<Kizazo> proyectiles) {

	        proyectiles.add(new Kizazo(x, y, 4, 0, textura));
	        proyectiles.add(new Kizazo(x, y, 2, 2, textura));
	        proyectiles.add(new Kizazo(x, y, 2, -2, textura));
	    }
	
	

}
