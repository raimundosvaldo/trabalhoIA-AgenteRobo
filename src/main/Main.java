package main;

import agente.Robo;
import ambiente.SuperficieDeMarte;

public class Main {
	
	public static void main(String[] args) {
		
		SuperficieDeMarte sm = new SuperficieDeMarte(10, 10, 10);
		Robo roboEmMarte = new Robo(sm);
		System.out.println(sm.getEstadoAtualAmbiente(roboEmMarte));
		System.out.println(roboEmMarte.getDirecao());
		roboEmMarte.atua();
		System.out.println(sm.getEstadoAtualAmbiente(roboEmMarte));
		roboEmMarte.atua();
		System.out.println(sm.getEstadoAtualAmbiente(roboEmMarte));
		roboEmMarte.atua();
		System.out.println(sm.getEstadoAtualAmbiente(roboEmMarte));
		roboEmMarte.atua();
		System.out.println(sm.getEstadoAtualAmbiente(roboEmMarte));
		roboEmMarte.atua();
		System.out.println(sm.getEstadoAtualAmbiente(roboEmMarte));
		roboEmMarte.atua();
		System.out.println(sm.getEstadoAtualAmbiente(roboEmMarte));
		
	}

}
