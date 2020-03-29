package ambiente;

import java.util.Random;

import agente.Robo;

public class Ambiente {

	private int altura, largura;
	private final Local[][] ambiente;
	private int totalDeObstaculos;

	public Ambiente(int altura, int largura, int totalDeObstaculos) {
		this.altura = altura;
		this.largura = largura;
		this.totalDeObstaculos = totalDeObstaculos;
		this.ambiente = new Local[altura][largura];
		this.montaAmbiente(totalDeObstaculos);
	}

	public int getTotalDeObstaculos() {
		return this.totalDeObstaculos;
	}

	public Local[][] getAmbiente() {
		return this.ambiente;
	}
	
	public int getAltura() {
		return this.altura;
	}
	
	public int getLargura() {
		return this.largura;
	}

	private void montaAmbiente(int totalDeObstaculos) {
		
		do {
			Random random = new Random();
			int x = random.nextInt(this.altura);
			int y = random.nextInt(this.largura);
			if (this.ambiente[x][y] == null && !isParede(x, y)) {
				this.ambiente[x][y] = new Local(TipoLocal.obstaculo);
				totalDeObstaculos--;
			}
		} while (totalDeObstaculos > 0);
		
		for (int x = 0; x < this.altura; x++) {
			for (int y = 0; y < this.largura; y++) {
				if (isParede(x, y)) {
					this.ambiente[x][y] = new Local(TipoLocal.parede);
				} else if (!isObstaculo(x, y)) {
					this.ambiente[x][y] = new Local(TipoLocal.livre);
				}
			}
		}
		
	}

	private boolean isParede(int x, int y) {
		return x == 0 || y == 0 || x == this.largura - 1 || y == this.altura - 1;
	}

	private boolean isObstaculo(int x, int y) {
		return this.ambiente[x][y] != null && !isParede(x, y)
				&& this.ambiente[x][y].getTipoLocal() == TipoLocal.obstaculo;
	}

	public boolean isEspacoLivre(int x, int y) {
		return this.ambiente[x][y] != null && this.ambiente[x][y].getTipoLocal() == TipoLocal.livre;
	}

	public String getEstadoAtualAmbiente(Robo robo) {
		
		StringBuilder ambiente = new StringBuilder();
		
		for (int x = 0; x < this.altura; x++) {
			for (int y = 0; y < this.largura; y++) {
				if (isEspacoLivre(x, y)) {
					if (x == robo.getPosicaoX() && y == robo.getPosicaoY()) {
						ambiente.append(" & ");
					} else {
						ambiente.append(this.ambiente[x][y].getTipoLocal());
					}
				} else if (isParede(x, y)) {
					ambiente.append(this.ambiente[x][y].getTipoLocal());
				} else {
					ambiente.append(this.ambiente[x][y].getTipoLocal());
				}
			}
			ambiente.append("\n");
		}
		return ambiente.toString();
	}
}