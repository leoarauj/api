package br.com.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.enuns.TipoContato;
import br.com.api.model.Contato;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação(DTO) da entidade {@link Contato}
 * 
 * @author Leonardo Araújo
 */
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(content = Include.NON_NULL)
public @Data class ContatoDTO {

	@ApiModelProperty(name = "Identificador primário de Contato", required = true)
	private Long id;

	@ApiModelProperty(name = "Login de usuário", required = true)
	private String contato;

	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataInicio;

	@ApiModelProperty(name = "Data de encerramento", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataFim;

	@ApiModelProperty(name = "Status(Ativo/Inativo) da empresa", required = true)
	private Long idStatus;

	@ApiModelProperty(name = "Tipo de contato", required = true)
	private TipoContato tipoContato;
}
