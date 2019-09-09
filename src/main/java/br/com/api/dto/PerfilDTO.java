package br.com.api.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.model.Perfil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação(DTO) da entidade {@link Perfil}
 * 
 * @author Leonardo Araújo
 */
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(content = Include.NON_NULL)
public @Data class PerfilDTO {

	@ApiModelProperty(name = "ID de Perfil de Acesso", required = true)
	private Long id;

	@NotBlank
	@ApiModelProperty(name = "Descrição do Perfil de Acesso", required = true)
	private String descricao;

	@ApiModelProperty(name = "Permissão de acesso ao sistema", required = true)
	private String permissao;
}
