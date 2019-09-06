package br.com.api.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.api.enuns.Sexo;

/**
 * Classe de conversão do enum {@link Sexo} para String
 * 
 * @author Leonardo Araújo
 */
@Converter(autoApply = true)
public class SexoStringConverter implements AttributeConverter<Sexo, String> {

	/**
	 * Função que converte para String o enum {@link Sexo}
	 */
	@Override
	public String convertToDatabaseColumn(Sexo sexo) {
		return (sexo == null ? null : sexo.getDescricao());
	}

	/**
	 * Retorna um enum de {@link Sexo} segundo descrição
	 */
	@Override
	public Sexo convertToEntityAttribute(String descricao) {
		return (descricao == null ? null : Sexo.getSexoPorDescricao(descricao));
	}

}
