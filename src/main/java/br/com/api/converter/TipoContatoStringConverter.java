package br.com.api.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.api.enuns.TipoContato;

/**
 * Classe de conversão do enum {@link TipoContato} para String
 * 
 * @author Leonardo Araújo
 */
@Converter(autoApply = true)
public class TipoContatoStringConverter implements AttributeConverter<TipoContato, String> {

	/**
	 * Função que converte para String o enum {@link TipoContato}
	 */
	@Override
	public String convertToDatabaseColumn(final TipoContato tipoContato) {
		return (tipoContato == null ? null : tipoContato.getDescricao());
	}

	/**
	 * Retorna um enum de {@link TipoContato} segundo sigla
	 */
	@Override
	public TipoContato convertToEntityAttribute(final String sigla) {
		return (sigla == null ? null : TipoContato.getTipoContatoPorSigla(sigla));
	}

}
