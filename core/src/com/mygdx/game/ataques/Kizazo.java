package com.mygdx.game.ataques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Kizazo {

	private float xSpeed;
	private float ySpeed;
	private boolean destroyed = false;
	private Sprite spr;
	private float delay = 0f;
	    
	    public Kizazo(float x, float y, float xSpeed, float ySpeed, Texture tx, float delay) {
	    	spr = new Sprite(tx);
	    	spr.setPosition(x, y);
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        this.delay = delay;
	        
	    }
	    
	    public Kizazo(float x, float y, float xSpeed, float ySpeed, Texture tx) {
	    	spr = new Sprite(tx);
	    	spr.setPosition(x, y);
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        
	    }
	    
	    public void update() {
	    	if(delay > 0)
	    	{
	    		delay -= Gdx.graphics.getDeltaTime();
	    		return;
	    	}
	    	
	        spr.setPosition(spr.getX()+xSpeed, spr.getY()+ySpeed);
	        if (spr.getX() < 0 || spr.getX()+spr.getWidth() > 900) {
	            destroyed = true;
	        }
	        if (spr.getY() < 0 || spr.getY()+spr.getHeight() > 800) {
	        	destroyed = true;
	        }
	        
	    }
	    
	    public void draw(SpriteBatch batch) {
	    	spr.draw(batch);
	    }
	    
	    	    
	    public Rectangle getArea() {
	    	return spr.getBoundingRectangle();
	    }

	    
	    public boolean isDestroyed() {return destroyed;}
	
}
