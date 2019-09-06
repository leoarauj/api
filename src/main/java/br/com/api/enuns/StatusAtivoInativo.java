package br.com.api.enuns;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.util.StringUtils;

/**
 * Enum com possíveis valores para definição de Status(Ativo/Inativo).
 * 
 * @author Leonardo Araújo
 */
public enum StatusAtivoInativo {
	ATIVO(1L, "Ativo"),
	INATIVO(2L, "Inativo");
	
	private Long id;
	private String descricao;

	/**
	 * Contrutor padrão do Enum {@link StatusAtivoInativo}
	 * 
	 * @param id
	 * @param Descricao
	 */
	private StatusAtivoInativo(final Long id, final String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Retorna {@link StatusAtivoInativo} segundo ID informado
	 * 
	 * @param descricao
	 * @return {@link StatusAtivoInativo}
	 */
	public static StatusAtivoInativo getStatusAtivoInativoPorId(final Long id) {
		if(Objects.isNull(id)) return null;

		return Arrays.asList(StatusAtivoInativo.values()).stream().filter(o -> o.getId() == id).findFirst().orElse(null);
	}

	/**
	 * Retorna {@link StatusAtivoInativo} segundo descrição informada
	 * 
	 * @param descricao
	 * @return {@link StatusAtivoInativo}
	 */
	public static StatusAtivoInativo getStatusAtivoInativoPorDescricao(final String descricao) {
		if(StringUtils.isEmpty(descricao)) return null;

		return Arrays.asList(StatusAtivoInativo.values()).stream().filter(o -> o.getDescricao().equalsIgnoreCase(descricao)).findFirst().orElse(null);
	}
}
