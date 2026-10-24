package com.mygdx.game.ataques;

import com.badlogic.gdx.utils.Array;

public interface AtaqueStrategy {
	Array<Kizazo> ejecutarAtaque(float x, float y);
}
