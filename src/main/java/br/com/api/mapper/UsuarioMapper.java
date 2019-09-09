package br.com.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.api.dto.UsuarioDTO;
import br.com.api.model.Usuario;

/**
 * Classe adapter referente a entidade {@link Usuario}.
 * 
 * @author Leonardo Araújo
 */
@Mapper(componentModel = "spring", uses = { PerfilMapper.class, PessoaMapper.class })
public interface UsuarioMapper {

	/**
	 * Converte a entidade {@link Usuario} em DTO {@link UsuarioDTO}
	 * 
	 * @param Usuario
	 * @return
	 */
	@Mapping(target = "idStatus", source = "status.id")
	public UsuarioDTO toDTO(Usuario usuario);

	/**
	 * Converte o DTO {@link UsuarioDTO} para entidade {@link Usuario}
	 * 
	 * @param UsuarioDTO
	 * @return
	 */
	@Mapping(target = "status", expression = "java(br.com.api.enuns.StatusAtivoInativo.getStatusAtivoInativoPorId(usuarioDTO.getIdStatus()))")
	@Mapping(target = "senha", expression = "java(br.com.api.util.SenhaUtils.gerarBCrypt(usuarioDTO.getSenha()))")
	public Usuario toEntity(UsuarioDTO usuarioDTO);
}
