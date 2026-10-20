package com.mygdx.game.personajes;

public abstract class Personaje {

	private int vida;
	private int poder;
	private int fase = 1;
	
	//constructor
	
	public Personaje(int vida, int poder)
	{
		this.vida = vida;
		this.poder = poder;
	}


	//metodos

	public abstract void herir(int v);
	
	public abstract void transformarse();
	
	
	public void quitarVida(int dano)
	{
		vida -= dano;
	}
	
	public boolean estadoPartida()
	{
		if(vida <= 0)
		{
			return true;
		}
		return false;
	}
	
	public void aumentarFase()
	{
		fase++;
	}
	
	public void sumaPoder(int cantPoder)
	{
		poder += cantPoder;
	}
	

	//getters y setters

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getPoder() {
		return poder;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	
	
}
