package ambiente;

import java.util.Random;

public class Espaco {
	
	private TipoEspaco tipoEspaco;
	private int temperatura;
	
	public Espaco(TipoEspaco tipo) {
		this.tipoEspaco = tipo;
		
		if(tipoEspaco == TipoEspaco.livre) {
			Random random = new Random();
			this.temperatura = (random.nextInt(301))-100;
		}
	}

	public int getTemperatura() {
		return this.temperatura;
	}
	
	public TipoEspaco getTipoEspaco() {
		return this.tipoEspaco;
	}
	
}
