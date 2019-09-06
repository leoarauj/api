package br.com.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação(DTO) da entidade Pessoa
 * 
 * @author Leonardo Araújo
 */
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(content = Include.NON_NULL)
public @Data class ClienteDTO {

	@ApiModelProperty(name = "ID de Pessoa", required = true)
	private Long id;

	@ApiModelProperty(name = "ID da Pessoa vinculada ao Cliente", required = true)
	private Long idPessoa;

	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataInicio;

	@ApiModelProperty(name = "Status(Ativo/Inativo) do cliente", required = true)
	private Long idStatus;
}
