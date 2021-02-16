package br.com.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.converter.StatusAtivoInativoStringConverter;
import br.com.api.converter.TipoContatoStringConverter;
import br.com.api.enuns.StatusAtivoInativo;
import br.com.api.enuns.TipoContato;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação da tabela <b>USUARIO</b>
 * 
 * @author Leonardo Araújo
 */
@Entity
@Table(name = "CONTATO")
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public @Data class Contato implements Serializable {

	private static final long serialVersionUID = -3784431865674928863L;

	@Id
	@ApiModelProperty(name = "Identificador primário de Contato", required = true)
	@Column(name = "PK_CONTATO")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@ApiModelProperty(name = "Login de usuário", required = true)
	@Column(name = "CONTATO", nullable = false, unique = true, updatable = false)
	private String contato;

	@NotNull
	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "DATA_INICIO", nullable = false)
	private LocalDateTime dataInicio;

	@ApiModelProperty(name = "Data de encerramento", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "DATA_FIM", nullable = false)
	private LocalDateTime dataFim;

	@NotNull
	@ApiModelProperty(name = "Status(Ativo/Inativo) da empresa", required = true)
	@Convert(converter = StatusAtivoInativoStringConverter.class)
	@Column(name = "STATUS", nullable = false, length = 20)
	private StatusAtivoInativo status;

	@NotNull
	@ApiModelProperty(name = "Tipo de contato", required = true)
	@Convert(converter = TipoContatoStringConverter.class)
	@Column(name = "TIPO_CONTATO", nullable = false, length = 35)
	private TipoContato tipoContato;
}













