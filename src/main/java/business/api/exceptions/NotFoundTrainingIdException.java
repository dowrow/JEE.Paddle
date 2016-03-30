package business.api.exceptions;

public class NotFoundTrainingIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "El entrenamiento referenciada no existe";

    public static final int CODE = 1;

    public NotFoundTrainingIdException() {
        this("");
    }

    public NotFoundTrainingIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
