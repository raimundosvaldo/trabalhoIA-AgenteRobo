package agente;

import java.util.ArrayList;
import java.util.Random;

import ambiente.Local;
import ambiente.SuperficieDeMarte;

public class Robo {

	private SuperficieDeMarte ambiente;
	private Posicao posicaoAtual = new Posicao();
	private Direcao direcaoAtual;
	private SensorTemperatura sensorTemperatura;
	private SensorObstaculos sensorObstaculos;
	private ArrayList<Posicao> locaisVisitados;
	private int maiorTemperatura, menorTemperatura;
	private int numLocaisNaoVisitados;

	public Robo(SuperficieDeMarte ambiente) {
		this.ambiente = ambiente;
		this.posicaoAtual = new Posicao();
		this.setEstadoInicial();
		this.sensorTemperatura = new SensorTemperatura();
		this.sensorObstaculos = new SensorObstaculos();
		this.locaisVisitados = new ArrayList<>();
		this.numLocaisNaoVisitados = (this.ambiente.getAltura() - 2) * (this.ambiente.getLargura() - 2)
				- this.ambiente.getTotalDeObstaculos();
	}

	public void setPosicao(int x, int y) {
		this.posicaoAtual.setCoordenada(x, y);
	}

	public int getPosicaoX() {
		return this.posicaoAtual.getCoordX();
	}

	public int getPosicaoY() {
		return this.posicaoAtual.getCoordY();
	}

	public Direcao getDirecao() {
		return this.direcaoAtual;
	}

	public void setDirecao(Direcao direcao) {
		this.direcaoAtual = direcao;
	}

	public void mudaDirecao() {
		
		switch (this.direcaoAtual) {
		case sul:
			this.direcaoAtual = Direcao.oeste;
			break;
		case leste:
			this.direcaoAtual = Direcao.sul;
			break;
		case norte:
			this.direcaoAtual = Direcao.leste;
			break;
		case oeste:
			this.direcaoAtual = Direcao.norte;
			break;
		}
		
		this.posicaoAtual.setDeslocamento(direcaoAtual);
	
	}

	public boolean isLocalVisitado(int x, int y) {
		for (Posicao pos : this.locaisVisitados) {
			if (pos.getCoordX() == x && pos.getCoordY() == y) {
				return true;
			}
		}
		return false;
	}

	public void desloca() {

		this.locaisVisitados.add(this.posicaoAtual);

		if (this.sensorObstaculos.acionaSensor(this.getProximoLocal())
				|| isLocalVisitado(this.posicaoAtual.getProxCoordX(), this.posicaoAtual.getProxCoordY())) {
			this.mudaDirecao();
			this.setDirecao(this.direcaoAtual);
		}

		this.setPosicao(this.posicaoAtual.getProxCoordX(), this.posicaoAtual.getProxCoordY());
		
	}

	public void atua() {

		Local[][] locais = this.ambiente.getAmbiente();

		verificaTemperaturaLocal(locais[posicaoAtual.getCoordX()][posicaoAtual.getCoordY()]);
		desloca();

	}

	private void verificaTemperaturaLocal(Local local) {
		int temperatura = this.sensorTemperatura.acionaSensor(local);
		if (temperatura >= this.maiorTemperatura) {
			this.maiorTemperatura = temperatura;
		}
		if (temperatura <= this.menorTemperatura) {
			this.menorTemperatura = temperatura;
		}
	}

	private Local getProximoLocal() {
		return this.ambiente.getAmbiente()[this.posicaoAtual.getProxCoordX()][this.posicaoAtual.getProxCoordY()];
	}

	private void setEstadoInicial() {

		Random random = new Random();
		int x;
		int y;

		do {
			x = random.nextInt(this.ambiente.getAltura());
			y = random.nextInt(this.ambiente.getLargura());
		} while (!this.ambiente.isEspacoLivre(x, y));

		this.setPosicao(x, y);

		int direcaoInicial = random.nextInt(4);

		switch (direcaoInicial) {
		case 0:
			setDirecao(Direcao.norte);
			break;
		case 1:
			setDirecao(Direcao.sul);
			break;
		case 2:
			setDirecao(Direcao.leste);
			break;
		case 3:
			setDirecao(Direcao.oeste);
			break;
		default:
			break;
		}
	}

}
