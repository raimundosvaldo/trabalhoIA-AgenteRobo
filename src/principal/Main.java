package principal;

import java.util.Scanner;

import agente.Robo;
import ambiente.Ambiente;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Um rob� explorando a superf�cie de Marte");
		System.out.println("Vamos configurar o ambiente. Ele ser� limitado por um quadrado");
		System.out.println("Indique um valor para a altura e largura do ambiente");
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.println("Informe um valor inteiro");
		}
		int dimensao = sc.nextInt();
		System.out.println("Quantos obst�culos ser�o inclu�dos no ambiente?");
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.println("Informe um valor inteiro");
		}
		int numeroDeObstaculos = sc.nextInt();
	
		System.out.println("Estamos preparando o ambiente... A posi��o dos obst�culos e do Rob� s�o aleat�rias");
		System.out.println("Legenda: X - Parede; O - Obst�culo; & - o Rob�");
		Thread.sleep(2500);
		
		Ambiente superficieDeMarte = new Ambiente(dimensao, dimensao, numeroDeObstaculos);
		Robo roboEmMarte = new Robo(superficieDeMarte);
		
		System.out.println("Pronto... Agora o rob� ");
		while (roboEmMarte.isTrabalhando()) {
			System.out.println(superficieDeMarte.getEstadoAtual(roboEmMarte));
			roboEmMarte.zeraTentativaDeMudarPosicao();
			roboEmMarte.atua();
			Thread.sleep(1500);
		}

		System.out.println(roboEmMarte.obterDesempenho());
		return;

	}

}
