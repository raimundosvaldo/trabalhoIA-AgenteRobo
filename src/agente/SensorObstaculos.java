package agente;

import ambiente.Espaco;
import ambiente.TipoEspaco;

public class SensorObstaculos{

	public boolean acionaSensor(Espaco espaco) {
		if(espaco.getTipoEspaco() != TipoEspaco.obstaculo 
				& espaco.getTipoEspaco() != TipoEspaco.parede) {
			return true;
		}
		return false;
	}
	
}
