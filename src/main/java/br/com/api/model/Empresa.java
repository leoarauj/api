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
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

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
 * Classe de representação da tabela <b>EMRPESA</b>
 * 
 * @author Leonardo Araújo
 */
@Entity
@Table(name = "EMPRESA")
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public @Data class Empresa implements Serializable {

	private static final long serialVersionUID = 7999045128077997466L;

	@Id
	@ApiModelProperty(name = "Identificador primário de empresa", required = true)
	@Column(name = "PK_EMPRESA")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@ApiModelProperty(name = "Nome fantasia da empresa", required = true)
	@Column(name = "NOME_FANTASIA", nullable = false, length = 150, unique = true)
	private String nomeFantasia;

	@NotBlank
	@Size(max = 18, min = 18, message = "CNPJ inválido")
	@ApiModelProperty(name = "CNPJ com mascára da empresa ex: 99.999.999/9999-99", required = true)
	@Column(name = "CNPJ", nullable = false, length = 18, unique = true)
	private String cnpj;

	@NotBlank
	@ApiModelProperty(name = "Razão social da empresa", required = true)
	@Column(name = "RAZAO_SOCIAL", nullable = false, length = 150, unique = true)
	private String razaoSocial;

	@ApiModelProperty(name = "Descrição da emrpesa", required = true)
	@Column(name = "DESCRICAO", nullable = false, length = 300)
	private String descricao;

	@ApiModelProperty(name = "Observação da emrpesa", required = true)
	@Column(name = "OBSERVACAO", nullable = false, length = 500)
	private String observacao;

	@ApiModelProperty(name = "Web site da empressa ex: www.empresa.com.br", required = true)
	@Column(name = "WEB_SITE", nullable = false, length = 100)
	private String webSite;

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
	
	@JoinTable(
        name = "EMPRESA_CONTATO",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"FK_EMPRESA", "FK_CONTATO"})},
        joinColumns = @JoinColumn(name = "FK_EMPRESA", referencedColumnName = "PK_EMPRESA", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "FK_CONTATO", referencedColumnName = "PK_CONTATO", nullable = false)
    )
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ApiModelProperty(name = "Lista de contatos da Empresa", required = true)
	private List<Contato> contatos;
}

















