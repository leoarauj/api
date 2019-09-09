package br.com.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de representação da tabela <b>PERFIL</b>
 * 
 * @author Leonardo Araújo
 */
@Entity
@Table(name = "PERFIL")
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public @Data class Perfil implements Serializable {

	private static final long serialVersionUID = 437762168066482202L;

	@Id
	@ApiModelProperty(name = "ID de Perfil de Acesso", required = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_PERFIL")
	private Long id;

	@NotBlank
	@ApiModelProperty(name = "Descrição do Perfil de Acesso", required = true)
	@Column(name = "DESCRICAO", nullable = false, length = 40)
	private String descricao;

	@NotBlank
	@ApiModelProperty(name = "Permissão de acesso ao sistema", required = true)
	@Column(name = "PERMISSAO", nullable = false)
	private String permissao;

}
