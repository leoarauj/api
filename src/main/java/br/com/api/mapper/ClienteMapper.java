package br.com.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.api.dto.ClienteDTO;
import br.com.api.model.Cliente;

/**
 * Classe adapter referente a entidade {@link Cliente}.
 * 
 * @author Leonardo Araújo
 */
@Mapper(componentModel = "spring")
public interface ClienteMapper {

	/**
	 * Converte a entidade {@link Cliente} em DTO {@link ClienteDTO}
	 * 
	 * @param cliente
	 * @return
	 */
	@Mapping(target = "idPessoa", source = "pessoa.id")
	@Mapping(target = "idStatus", source = "status.id")
	public ClienteDTO toDTO(final Cliente cliente);

	/**
	 * Converte o DTO {@link ClienteDTO} para entidade {@link Cliente}
	 * 
	 * @param clienteDTO
	 * @return
	 */
	@Mapping(target = "pessoa.id", source = "idPessoa")
	@Mapping(target = "status", expression = "java(br.com.api.enuns.StatusAtivoInativo.getStatusAtivoInativoPorId(clienteDTO.getIdStatus()))")
	public Cliente toEntity(final ClienteDTO clienteDTO);
}
