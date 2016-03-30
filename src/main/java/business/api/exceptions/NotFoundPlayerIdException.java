package business.api.exceptions;

public class NotFoundPlayerIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encuentra el identificador de alumno en el entrenamiento";

    public static final int CODE = 1;

    public NotFoundPlayerIdException() {
        this("");
    }

    public NotFoundPlayerIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
