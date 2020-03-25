package agente;

public class Robo {
		
	private PosicaoXY posicao = new PosicaoXY(); 
	private SensorTemperatura sensorTemperatura = new SensorTemperatura();
	private SensorObstaculos sensorObstaculos = new SensorObstaculos();

	public void setPosicao(int posX, int posY) {
		this.posicao.setPosicao(posX, posY);;
	}
	
	public int getPosicaoX() {
		return this.posicao.getPosX();
	}
	
	public int getPosicaoY() {
		return this.posicao.getPosY();
	}
	
	
}
