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
public @Data class PessoaDTO {

	@ApiModelProperty(name = "ID de Pessoa", required = false)
	private Long id;

	@ApiModelProperty(name = "Denominação de pessoa Física ou Jurídica", required = true)
	private String denominacao;

	@ApiModelProperty(name = "CPF ou CPJ de Pessoa", required = true)
	private Long cpfCnpj;

	@ApiModelProperty(name = "E-mail de Pessoa", required = false)
	private String email;

	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataInicio;

	@ApiModelProperty(name = "Data de nascimento", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataNascimento;

	@ApiModelProperty(name = "Coluna [descricao] da tabela TipoPessoa", required = true)
	private Long idTipoPessoa;

	@ApiModelProperty(name = "Enum com possíveis valores de identidades de gênero", required = true)
	private Long idSexo;
}
