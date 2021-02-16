package br.com.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectError {

	private final String message;
    private final String field;
    private final Object parameter;
}
