package business.api.exceptions;

public class InvalidTrainingException extends ApiException {

	private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Creación de entrenamiento no válida";

    public static final int CODE = 1;

    public InvalidTrainingException(String description, int code) {
    	this("");
	}

    public InvalidTrainingException(String detail) {
  	  super(DESCRIPTION + ". " + detail, CODE);
	}

}
