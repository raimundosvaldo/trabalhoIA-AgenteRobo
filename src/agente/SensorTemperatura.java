package agente;

import ambiente.Local;

public class SensorTemperatura {

	public int acionaSensor(Local espaco) {
		return espaco.getTemperatura();
	}
	
}
