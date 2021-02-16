package br.com.api.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.api.logger.RegisterLogger;
import br.com.api.response.ErrorResponse;
import br.com.api.response.ObjectError;
import br.com.api.response.RestResponse;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RestResponseMessageException.class)
	@ResponseBody
	public final ResponseEntity<RestResponse> handleAllExceptions(final RestResponseMessageException exceptionResponse, final WebRequest request) {
		registerErrorInLogger(exceptionResponse);

		HttpStatus status = HttpStatus.valueOf(exceptionResponse.getStatus());

		RestResponse response = new RestResponse();

		response.setCode(status.value());
		response.setStatus(status);
		response.setMessage(exceptionResponse.getMessage());
		response.setDate(LocalDateTime.now());
		response.setPath(request.getDescription(false));

		return new ResponseEntity<RestResponse>(response, status);
	}

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

	private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status,
			List<ObjectError> errors) {
		return new ErrorResponse("Campos inválidos!", status.value(), status.getReasonPhrase(),
				ex.getBindingResult().getObjectName(), errors);
	}

	private List<ObjectError> getErrors(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new ObjectError(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
				.collect(Collectors.toList());
	}

	/**
	 * Para toda excessão externalizada é gerado o logger de erro automáticamente
	 * 
	 * @param exceptionResponse
	 */
	private void registerErrorInLogger(final RestResponseMessageException exceptionResponse) {
		RegisterLogger.error(exceptionResponse);
	}
}
