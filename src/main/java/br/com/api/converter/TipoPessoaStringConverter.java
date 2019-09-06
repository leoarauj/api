package br.com.api.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.api.enuns.TipoPessoa;

/**
 * Classe de conversão do enum {@link TipoPessoa} para String
 * 
 * @author Leonardo Araújo
 */
@Converter(autoApply = true)
public class TipoPessoaStringConverter implements AttributeConverter<TipoPessoa, String> {

	/**
	 * Função que converte para String o enum {@link TipoPessoa}
	 */
	@Override
	public String convertToDatabaseColumn(TipoPessoa tipoPessoa) {
		return (tipoPessoa == null ? null : tipoPessoa.getSigla());
	}

	/**
	 * Retorna um enum de {@link TipoPessoa} segundo sigla
	 */
	@Override
	public TipoPessoa convertToEntityAttribute(String sigla) {
		return (sigla == null ? null : TipoPessoa.getTipoPessoaPorSigla(sigla));
	}

}
