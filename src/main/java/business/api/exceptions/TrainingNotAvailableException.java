package business.api.exceptions;

public class TrainingNotAvailableException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "El entrenamiento seleccionado no admite más alumnos";

    public static final int CODE = 1;

    public TrainingNotAvailableException() {
        this("");
    }

    public TrainingNotAvailableException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
