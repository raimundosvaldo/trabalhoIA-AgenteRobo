package ambiente;

public enum TipoLocal {

	obstaculo, parede, livre;

	public String toString() {
		switch (this) {

		case obstaculo:
			return " O ";
		case parede:
			return " X ";
		case livre:
			return "   ";
		default:
			return " ? ";

		}
	}

}