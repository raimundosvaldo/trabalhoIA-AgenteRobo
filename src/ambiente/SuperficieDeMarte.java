package ambiente;

import java.util.Random;

import agente.Robo;

public class SuperficieDeMarte {

	private int altura, largura;
	private final Espaco[][] ambiente;
	private int totalDeObstaculos;
	private Robo robo;

	public SuperficieDeMarte(int altura, int largura, int totalDeObstaculos) {
		this.altura = altura;
		this.largura = largura;
		this.totalDeObstaculos = totalDeObstaculos;
		this.ambiente = new Espaco[altura][largura];
		this.robo = new Robo();
		this.montaAmbiente(totalDeObstaculos);
		this.posicionaRobo();
	}

	public int getTotalDeObstaculos() {
		return this.totalDeObstaculos;
	}

	public Espaco[][] getAmbiente() {
		return this.ambiente;
	}

	private void montaAmbiente(int totalDeObstaculos) {
		
		do {
			Random random = new Random();
			int x = random.nextInt(this.altura);
			int y = random.nextInt(this.largura);
			if (this.ambiente[x][y] == null && !isParede(x, y)) {
				this.ambiente[x][y] = new Espaco(TipoEspaco.obstaculo);
				totalDeObstaculos--;
			}
		} while (totalDeObstaculos > 0);
		
		for (int i = 0; i < this.altura; i++) {
			for (int j = 0; j < this.largura; j++) {
				if (isParede(i, j)) {
					this.ambiente[i][j] = new Espaco(TipoEspaco.parede);
				} else if (!isObstaculo(i, j)) {
					this.ambiente[i][j] = new Espaco(TipoEspaco.livre);
				}
			}
		}
		
	}

	private void posicionaRobo() {
		
		Random random = new Random();
		int posX;
		int posY;
		
		do {
			posX = random.nextInt(altura);
			posY = random.nextInt(largura);
		} while (!isEspacoLivre(posX, posY));
		
		this.robo.setPosicao(posX, posY);
	}

	private boolean isParede(int x, int y) {
		return x == 0 || y == 0 || x == this.altura - 1 || y == this.largura - 1;
	}

	private boolean isObstaculo(int x, int y) {
		return this.ambiente[x][y] != null && !isParede(x, y)
				&& this.ambiente[x][y].getTipoEspaco() == TipoEspaco.obstaculo;
	}

	private boolean isEspacoLivre(int x, int y) {
		return this.ambiente[x][y] != null && this.ambiente[x][y].getTipoEspaco() == TipoEspaco.livre;
	}

	public void mostraAmbiente() {
		for (int i = 0; i < this.altura; i++) {
			for (int j = 0; j < this.largura; j++) {
				if (isEspacoLivre(i, j)) {
					if (i == this.robo.getPosicaoX() && j == this.robo.getPosicaoY()) {
						System.out.print(" & ");
					} else {
						System.out.print(this.ambiente[i][j].getTipoEspaco());
					}
				} else if (isParede(i, j)) {
					System.out.print(this.ambiente[i][j].getTipoEspaco());
				} else {
					System.out.print(this.ambiente[i][j].getTipoEspaco());
				}
			}
			System.out.println();
		}
	}
}