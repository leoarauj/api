package br.com.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.converter.SexoStringConverter;
import br.com.api.converter.TipoPessoaStringConverter;
import br.com.api.enuns.Sexo;
import br.com.api.enuns.TipoPessoa;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação da tabela Pessoa
 * 
 * @author Leonardo Araújo
 */
@Entity
@Table(name = "PESSOA")
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public @Data class Pessoa implements Serializable {

	private static final long serialVersionUID = -8535937850650801237L;

	@Id
	@ApiModelProperty(name = "ID de Pessoa", required = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_PESSOA")
	private Long id;

	@NotBlank
	@ApiModelProperty(name = "Denominação de pessoa Física ou Jurídica", required = true)
	@Column(name = "DENOMINACAO", nullable = false, length = 100)
	private String denominacao;

	@NotNull
	@ApiModelProperty(name = "CPF ou CPJ de Pessoa", required = true)
	@Column(name = "CPF_CNPJ", nullable = false, unique = true)
	private Long cpfCnpj;

	@ApiModelProperty(name = "E-mail de Pessoa", required = false)
	@Column(name = "EMAIL", nullable = true, length = 50)
	private String email;

	@NotNull
	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "DATA_INICIO", nullable = false)
	private LocalDateTime dataInicio;

	@ApiModelProperty(name = "Data de nascimento", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private LocalDateTime dataNascimento;

	@NotNull
	@ApiModelProperty(name = "Tipo de Pessoa", required = true)
	@Convert(converter = TipoPessoaStringConverter.class)
	@Column(name = "TIPO_PESSOA", nullable = false, length = 2)
	private TipoPessoa tipoPessoa;

	@NotNull
	@ApiModelProperty(name = "Gênero", required = true)
	@Convert(converter = SexoStringConverter.class)
	@Column(name = "SEXO", nullable = false, length = 15)
	private Sexo sexo;
	
}
