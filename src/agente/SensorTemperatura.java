package agente;

import ambiente.Espaco;

public class SensorTemperatura {

	public int acionaSensor(Espaco espaco) {
		return espaco.getTemperatura();
	}
	
}
