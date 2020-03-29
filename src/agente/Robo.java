package agente;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Random;

import ambiente.Local;
import ambiente.Ambiente;

public class Robo {

	private Ambiente ambiente;
	private Posicao posicaoAtual;
	private Direcao direcaoAtual;
	private SensorTemperatura sensorTemperatura;
	private SensorObstaculos sensorObstaculos;
	private HashSet<Posicao> locaisVisitados;
	private int maiorTemperatura, menorTemperatura;
	private int numLocaisNaoVisitados;
	private int contaTentativaDeMudarPosicao;

	public Robo(Ambiente ambiente) {
		this.ambiente = ambiente;
		this.posicaoAtual = new Posicao();
		this.sensorTemperatura = new SensorTemperatura();
		this.sensorObstaculos = new SensorObstaculos();
		this.setEstadoInicial();
		this.locaisVisitados = new HashSet<>();
		this.numLocaisNaoVisitados = (this.ambiente.getLargura() - 2) * (this.ambiente.getAltura() - 2)
				- this.ambiente.getTotalDeObstaculos();
	}

	public int getMaiorTemperatura() {
		return maiorTemperatura;
	}

	public int getMenorTemperatura() {
		return menorTemperatura;
	}

	public int getNumLocaisNaoVisitados() {
		return numLocaisNaoVisitados;
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

	public void atua() {

		if (this.contaTentativaDeMudarPosicao >= 4) {
			return;
		}

		if (this.sensorObstaculos.acionaSensor(this.getProximoLocal())
				|| isLocalVisitado(this.posicaoAtual.getProxCoordX(), this.posicaoAtual.getProxCoordY())) {
			this.mudaDirecao();
			this.contaTentativaDeMudarPosicao++;
			this.atua();
		} else {
			this.locaisVisitados.add(new Posicao(this.getPosicaoX(), this.getPosicaoY()));
			this.verificaTemperaturaLocal();
			this.numLocaisNaoVisitados--;
			this.setPosicao(this.posicaoAtual.getProxCoordX(), this.posicaoAtual.getProxCoordY());
		}

	}
	
	public String obterDesempenho() {

		NumberFormat percent = NumberFormat.getPercentInstance();

		StringBuilder desempenho = new StringBuilder("Desempenho do Agente");

		double taxaDeCobertura = (double) this.locaisVisitados.size()
				/ (this.locaisVisitados.size() + this.numLocaisNaoVisitados);

		return desempenho.append("Locais Visitados: " + this.locaisVisitados.size()).append("\n")
				.append("Locais não visitados: " + this.numLocaisNaoVisitados).append("\n")
				.append("Taxa de Cobertura: " + percent.format(taxaDeCobertura)).append("\n")
				.append("Menor Temperatura: " + this.menorTemperatura).append("\n")
				.append("Maior Tempertadura: " + this.maiorTemperatura).toString();

	}
	
	public boolean isTrabalhando() {
		return this.contaTentativaDeMudarPosicao < 4 && this.numLocaisNaoVisitados > 0;
	}

	public void zeraTentativaDeMudarPosicao() {
		this.contaTentativaDeMudarPosicao = 0;
	}	
	
	private void mudaDirecao() {

		switch (this.direcaoAtual) {
		case sul:
			this.direcaoAtual = Direcao.oeste;
			break;
		case leste:
			this.direcaoAtual = Direcao.norte;
			break;
		case norte:
			this.direcaoAtual = Direcao.sul;
			break;
		case oeste:
			this.direcaoAtual = Direcao.leste;
			break;
		}

		this.posicaoAtual.setDeslocamento(direcaoAtual);

	}

	private void verificaTemperaturaLocal() {
		int temperatura = this.sensorTemperatura.acionaSensor(this.getLocalAtual());
		if (temperatura >= this.maiorTemperatura) {
			this.maiorTemperatura = temperatura;
		}
		if (temperatura <= this.menorTemperatura) {
			this.menorTemperatura = temperatura;
		}
	}

	private boolean isLocalVisitado(int x, int y) {
		for (Posicao pos : this.locaisVisitados) {
			if (pos.getCoordX() == x && pos.getCoordY() == y) {
				return true;
			}
		}
		return false;
	}

	private Local getProximoLocal() {
		return this.ambiente.getAmbiente()[this.posicaoAtual.getProxCoordX()][this.posicaoAtual.getProxCoordY()];
	}

	private Local getLocalAtual() {
		return this.ambiente.getAmbiente()[this.getPosicaoX()][this.getPosicaoY()];
	}

	private void setEstadoInicial() {

		Random random = new Random();
		int x;
		int y;

		do {
			x = random.nextInt(this.ambiente.getLargura());
			y = random.nextInt(this.ambiente.getAltura());
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

		this.posicaoAtual.setDeslocamento(this.direcaoAtual);
		this.maiorTemperatura = this.menorTemperatura = this.sensorTemperatura.acionaSensor(this.getLocalAtual());

	}

}