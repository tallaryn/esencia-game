package com.mygdx.game.personajes;

public abstract class Personaje {

	protected int vida;
	protected int poder;
	protected int fase = 1;
	protected int maxFase = 2;
	
	//constructor
	
	public Personaje(int vida, int poder)
	{
		this.vida = vida;
		this.poder = poder;
	}


	//metodos

	public final void TRANSFORMARSE(){ //TemplateMethod
		
		if(puedeTransformarse() && fase < maxFase) {
			fase++;
			aplicarTransformacion();
			finalTransformacion();
		}
		
	}
	
	
	public abstract void herir(int v);

	
	
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
	
	public void sumaPoder(int cantPoder)
	{
		poder += cantPoder;
	}
	
	protected abstract boolean puedeTransformarse(); //Cada personaje define su criterio de transformacion.
	protected abstract void aplicarTransformacion(); //Cada personaje define como se transforma.
	protected void finalTransformacion() {} //Cada personaje define como finaliza su transformacion. Opcional

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
