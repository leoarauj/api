package br.com.api.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.api.enuns.StatusAtivoInativo;

/**
 * Classe de conversão do enum {@link StatusAtivoInativo} para String
 * 
 * @author Leonardo Araújo
 */
@Converter(autoApply = true)
public class StatusAtivoInativoStringConverter implements AttributeConverter<StatusAtivoInativo, String> {

	/**
	 * Função que converte para String o enum {@link StatusAtivoInativo}
	 */
	@Override
	public String convertToDatabaseColumn(StatusAtivoInativo status) {
		return (status == null ? null : status.getDescricao());
	}

	/**
	 * Retorna um enum de {@link StatusAtivoInativo} segundo descrição
	 */
	@Override
	public StatusAtivoInativo convertToEntityAttribute(String descricao) {
		return (descricao == null ? null : StatusAtivoInativo.getStatusAtivoInativoPorDescricao(descricao));
	}

}
