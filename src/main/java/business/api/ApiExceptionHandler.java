package business.api;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import business.api.exceptions.AlreadyExistCourtIdException;
import business.api.exceptions.AlreadyExistUserFieldException;
import business.api.exceptions.ApiException;
import business.api.exceptions.ErrorMessage;
import business.api.exceptions.InvalidCourtReserveException;
import business.api.exceptions.InvalidDateException;
import business.api.exceptions.InvalidTrainingException;
import business.api.exceptions.InvalidUserFieldException;
import business.api.exceptions.MalformedHeaderException;
import business.api.exceptions.NotFoundCourtIdException;
import business.api.exceptions.NotFoundPlayerIdException;
import business.api.exceptions.NotFoundTrainingIdException;
import business.api.exceptions.UnauthorizedException;
import business.api.exceptions.NotFoundUserIdException;
import business.api.exceptions.TrainingNotAvailableException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundUserIdException.class, NotFoundCourtIdException.class, 
    	NotFoundPlayerIdException.class, NotFoundTrainingIdException.class })
    @ResponseBody
    public ErrorMessage notFoundRequest(ApiException exception) {
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        return apiErrorMessage;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public void unauthorized(ApiException exception) {
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        LogManager.getLogger(this.getClass()).info("  ERROR: UNAUTHORIZED, " + apiErrorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MalformedHeaderException.class, InvalidUserFieldException.class, InvalidDateException.class})
    @ResponseBody
    public ErrorMessage badRequest(ApiException exception) {
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        return apiErrorMessage;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({AlreadyExistCourtIdException.class, AlreadyExistUserFieldException.class, 
    	InvalidTrainingException.class, AlreadyExistUserFieldException.class, 
    	InvalidCourtReserveException.class, TrainingNotAvailableException.class})
    @ResponseBody
    public ErrorMessage conflictRequest(ApiException exception) {
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        return apiErrorMessage;
    }

    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // @ExceptionHandler({Exception.class})
    // @ResponseBody
    // public ErrorMessage exception(Exception exception) {
    // ErrorMessage apiErrorMessage = new ErrorMessage(exception);
    // return apiErrorMessage;
    // }

}
