package br.com.api.mapper;

import org.mapstruct.Mapper;

import br.com.api.dto.PerfilDTO;
import br.com.api.model.Perfil;

/**
 * Classe adapter referente a entidade {@link Perfil}.
 * 
 * @author Leonardo Araújo
 */
@Mapper(componentModel = "spring")
public interface PerfilMapper {

	/**
	 * Converte a entidade {@link Perfil} em DTO {@link PerfilDTO}
	 * 
	 * @param Perfil
	 * @return
	 */
	public PerfilDTO toDTO(Perfil Perfil);

	/**
	 * Converte o DTO {@link PerfilDTO} para entidade {@link Perfil}
	 * 
	 * @param PerfilDTO
	 * @return
	 */
	public Perfil toEntity(PerfilDTO PerfilDTO);
}
