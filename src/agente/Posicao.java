package agente;

public class Posicao {
	
	private int coordX, coordY;
	private int deslocX, deslocY;
	
	public void setCoordenada(int x, int y) {
		this.coordX = x;
		this.coordY = y;
	}
	
	public int getCoordX() {
		return this.coordX;
	}
	
	public int getCoordY() {
		return this.coordY;
	}
	
	public int getDeslocX() {
		return this.deslocX;
	}
	
	public int getDeslocY() {
		return this.deslocY;
	}
	
	public int getProxCoordX() {
		return (this.coordX + this.deslocX);
	}

	public int getProxCoordY() {
		return (this.coordY + this.deslocY);
	}
	
	public void setDeslocamento(Direcao direcao) {
		switch (direcao) {
		case norte:
			this.deslocX = 0;
			this.deslocY = -1;
			break;
		case sul:
			this.deslocX = 0;
			this.deslocY = 1;
			break;
		case oeste:
			this.deslocX = -1;
			this.deslocY = 0;
			break;
		case leste:
			this.deslocX = 1;
			this.deslocY = 0;
			break;
		default:
			break;
		}
	}

}
