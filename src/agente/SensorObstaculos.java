package agente;

import ambiente.Local;
import ambiente.TipoLocal;

public class SensorObstaculos{

	public boolean acionaSensor(Local local) {
		if(local.getTipoLocal() == TipoLocal.obstaculo 
				|| local.getTipoLocal() == TipoLocal.parede) {
			return true;
		}
		return false;
	}
	
}
