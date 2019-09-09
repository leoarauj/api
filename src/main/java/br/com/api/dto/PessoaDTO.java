package br.com.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.model.Pessoa;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação(DTO) da entidade {@link Pessoa}
 * 
 * @author Leonardo Araújo
 */
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(content = Include.NON_NULL)
public @Data class PessoaDTO {

	@ApiModelProperty(name = "ID de Pessoa", required = false)
	private Long id;

	@ApiModelProperty(name = "Nome completo de Pessoa", required = true)
	private String nome;

	@ApiModelProperty(name = "CPF de Pessoa", required = true)
	private Long cpf;

	@ApiModelProperty(name = "RG de Pessoa", required = true)
	private Long rg;

	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataInicio;

	@ApiModelProperty(name = "Data de nascimento", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataNascimento;

	@ApiModelProperty(name = "Enum com possíveis valores de identidade de gênero", required = true)
	private Long idSexo;
}
