package br.com.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.api.dto.PessoaDTO;
import br.com.api.model.Pessoa;

/**
 * Classe adapter referente a entidade {@link Pessoa}.
 * 
 * @author Leonardo Araújo
 */
@Mapper(componentModel = "spring")
public interface PessoaMapper {

	/**
	 * Converte a entidade {@link Pessoa} em DTO {@link PessoaDTO}
	 * 
	 * @param pessoa
	 * @return
	 */
	@Mapping(target = "idSexo", source = "sexo.id")
	public PessoaDTO toDTO(Pessoa pessoa);

	/**
	 * Converte o DTO {@link PessoaDTO} para entidade {@link Pessoa}
	 * 
	 * @param pessoaDTO
	 * @return
	 */
	@Mapping(target = "sexo", expression = "java(br.com.api.enuns.Sexo.getSexoPorId(pessoaDTO.getIdSexo()))")
	public Pessoa toEntity(PessoaDTO pessoaDTO);

//	@InheritInverseConfiguration
//	public PessoaDTO fieldToFieldDto(Pessoa model); // para o relacionamento List fields
}
