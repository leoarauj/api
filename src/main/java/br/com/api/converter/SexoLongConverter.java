package br.com.api.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.api.enuns.Sexo;

/**
 * Classe de conversão de Sexo para String
 * 
 * @author Leonardo Araújo
 */
@Converter(autoApply = true)
public class SexoLongConverter implements AttributeConverter<Sexo, Long>  {

	@Override
	public Long convertToDatabaseColumn(Sexo attribute) {
		return (attribute == null ? null : attribute.getId());
	}

	@Override
	public Sexo convertToEntityAttribute(Long dbData) {
		return (dbData == null ? null : Sexo.getSexoPorId(dbData));
	}

}
