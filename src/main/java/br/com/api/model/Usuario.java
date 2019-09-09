package br.com.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.api.converter.StatusAtivoInativoStringConverter;
import br.com.api.enuns.StatusAtivoInativo;
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
@Table(name = "USUARIO")
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public @Data class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = -762947618414560070L;

	@Id
	@ApiModelProperty(name = "ID de Usuário", required = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_USUARIO")
	private Long id;

	@NotBlank
	@ApiModelProperty(name = "Login de usuário", required = true)
	@Column(name = "LOGIN", nullable = false, unique = true, updatable = false)
	private String login;

	@NotBlank
	@ApiModelProperty(name = "Senha de usuário", required = true)
	@JsonIgnore
	@Column(name = "SENHA", nullable = false)
	private String senha;

	@NotNull
	@ApiModelProperty(name = "Pessoa", required = true)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PESSOA", unique = true, updatable = false)
	private Pessoa pessoa;

	@NotNull
	@ApiModelProperty(name = "Data início de cadastro", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "DATA_INICIO", updatable = false, nullable = false)
	private LocalDateTime dataInicio;

	@NotNull
	@ApiModelProperty(name = "Status(Ativo/Inativo) de Usuário", required = true)
	@Convert(converter = StatusAtivoInativoStringConverter.class)
	@Column(name = "STATUS", nullable = false, length = 10)
	private StatusAtivoInativo status;

	@ManyToMany(fetch = FetchType.EAGER)
	@ApiModelProperty(name = "Perfis de Usuário", required = true)
	@JoinTable(
        name = "USUARIO_PERFIL",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"FK_USUARIO", "FK_PERFIL"})},
        joinColumns = @JoinColumn(name = "FK_USUARIO", referencedColumnName = "PK_USUARIO", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "FK_PERFIL", referencedColumnName = "PK_PERFIL", nullable = false)
    )
	private List<Perfil> perfis;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
