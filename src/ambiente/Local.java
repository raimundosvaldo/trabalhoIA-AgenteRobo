package ambiente;

import java.util.Random;

public class Local {
	
	private TipoLocal tipoLocal;
	private int temperatura;
	
	public Local(TipoLocal tipo) {
		this.tipoLocal = tipo;
		
		if(tipoLocal == TipoLocal.livre) {
			Random random = new Random();
			this.temperatura = (random.nextInt(301))-100;
		}
	}

	public int getTemperatura() {
		return this.temperatura;
	}
	
	public TipoLocal getTipoLocal() {
		return this.tipoLocal;
	}

}
