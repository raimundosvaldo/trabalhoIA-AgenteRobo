package agente;

public class PosicaoXY {
	
	private int posX, posY;
	private int deslocX, deslocY;
	
	public void setPosicao(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public int getDeslocX() {
		return this.deslocX;
	}
	
	public int getDeslocY() {
		return this.deslocY;
	}
	
	public int getProximaPosicaoX() {
		return this.posX + this.deslocX;
	}

	public int getProximaPosicaoY() {
		return this.posY + this.deslocY;
	}
	
	public void setDirecao(DirecaoRobo direcao) {
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
