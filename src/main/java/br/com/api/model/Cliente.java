package br.com.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.converter.StatusAtivoInativoStringConverter;
import br.com.api.enuns.StatusAtivoInativo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação da tabela Cliente
 * 
 * @author Leonardo Araújo
 */
@Entity
@Table(name = "CLIENTE")
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public @Data class Cliente implements Serializable {

	private static final long serialVersionUID = 1823789929092241670L;

	@Id
	@ApiModelProperty(name = "ID de Pessoa", required = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_CLIENTE")
	private Long id;

	@NotNull
	@ApiModelProperty(name = "Pessoa", required = true)
	@JoinColumn(name = "PK_PESSOA", nullable = false)
	@Column(name = "FK_PESSOA")
	private Pessoa pessoa;

	@NotNull
	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "DATA_INICIO", nullable = false)
	private LocalDateTime dataInicio;

	@NotNull
	@ApiModelProperty(name = "Status(Ativo/Inativo) do cliente", required = true)
	@Convert(converter = StatusAtivoInativoStringConverter.class)
	@Column(name = "STATUS", nullable = false, length = 15)
	private StatusAtivoInativo status;

}
