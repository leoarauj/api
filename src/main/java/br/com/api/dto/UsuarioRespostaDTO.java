package br.com.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.model.Usuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação(DTO) da entidade {@link Usuario},
 * somente com valores necessários de resposta.
 * 
 * @author Leonardo Araújo
 */
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(content = Include.NON_NULL)
public @Data class UsuarioRespostaDTO {

	@ApiModelProperty(name = "ID de Usuário", required = true)
	private Long id;

	@ApiModelProperty(name = "Login de usuário", required = true)
	private String login;

	@ApiModelProperty(name = "Pessoa", required = true)
	private PessoaDTO pessoa;

	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataInicio;

	@ApiModelProperty(name = "Status(Ativo/Inativo) de Usuário", required = true)
	private Long idStatus;

	@ApiModelProperty(name = "Perfis de Usuário", required = true)
	private List<PerfilDTO> perfis;
}
