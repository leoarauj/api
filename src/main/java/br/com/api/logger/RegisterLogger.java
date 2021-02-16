package br.com.api.logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import br.com.api.exception.RestResponseMessageException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class RegisterLogger {

	/**
	 * Registra logger de <b>Erro</b> 
	 * 
	 * @param error
	 */
	public static final void error(final RestResponseMessageException error) {
		if(Objects.nonNull(error)) {
			String msg = descriptionOfLogger(error.getStatus(), error.getMessage());
			log.error(msg);
		}
	}

	/**
	 * Registra logger de <b>Info</b> 
	 * 
	 * @param message
	 */
	public static final void info(final String... message) {
		if(!Arrays.asList(message).isEmpty()) {
			String msg = descriptionOfLogger(null, message);
			log.info(msg);
		}
	}

	/**
	 * Registra logger de <b>Debug</b> 
	 * 
	 * @param message
	 */
	public static final void debug(final String... message) {
		if(!Arrays.asList(message).isEmpty()) {
			String msg = descriptionOfLogger(null, message);
			log.debug(msg);
		}
	}

	/**
	 * Recebe uma mensagem e formata seguindo o padrão. <br>
	 * Ex: <br>
	 * 
	 * [ Status HTTP: 400 - Description: Campos obrigatórios não informados ] 
	 * 
	 * @param message
	 * @return
	 */
	private static final String descriptionOfLogger(final Integer statusHttp, final String... message) {
		List<String> concat = Arrays.asList(message);

		if(Objects.isNull(concat)) return null;

		StringBuilder msg = new StringBuilder();

		msg.append(" [ ");

		if(statusHttp != null) {
			msg.append("Status Http: ");
			msg.append(statusHttp);
			msg.append(" - ");
		}

		msg.append(" Description: ");
		concat.stream().forEach(o -> msg.append(o));
		msg.append(" ] ");

		return msg.toString();
	}
}
