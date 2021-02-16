package br.com.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.converter.SexoStringConverter;
import br.com.api.enuns.Sexo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação da tabela <b>PESSOA</b>
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
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@ApiModelProperty(name = "ID de Pessoa", required = true)
	@Column(name = "PK_PESSOA")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@ApiModelProperty(name = "Nome completo de Pessoa", required = true)
	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;

	@NotNull
	@ApiModelProperty(name = "CPF de Pessoa", required = true)
	@Column(name = "CPF", nullable = false, unique = true)
	private Long cpf;

	@NotNull
	@ApiModelProperty(name = "RG de Pessoa", required = true)
	@Column(name = "RG", nullable = true, unique = true)
	private Long rg;

	@NotNull
	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "DATA_INICIO", nullable = false)
	private LocalDateTime dataInicio;

	@ApiModelProperty(name = "Data de nascimento", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "DATA_NASCIMENTO", nullable = true)
	private LocalDateTime dataNascimento;

	@NotNull
	@ApiModelProperty(name = "Gênero", required = true)
	@Convert(converter = SexoStringConverter.class)
	@Column(name = "SEXO", nullable = false, length = 15)
	private Sexo sexo;

	@JoinTable(
        name = "PESSOA_CONTATO",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"FK_PESSOA", "FK_CONTATO"})},
        joinColumns = @JoinColumn(name = "FK_PESSOA", referencedColumnName = "PK_PESSOA", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "FK_CONTATO", referencedColumnName = "PK_CONTATO", nullable = false)
    )
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ApiModelProperty(name = "Lista de contatos de Pessoa", required = true)
	private List<Contato> contatos;

}
