package br.com.api.enuns;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.util.StringUtils;

/**
 * Classe de representação da tabela TipoPessoa
 * 
 * @author Leonardo Araújo
 */
public enum TipoPessoa {

	PESSOA_FISICA(1L, "Pessoa Física", "PF"),
	PESSOA_JURIDICA(2L, "Pessoa Jurídica", "PJ");

	private Long id;
	private String descricao;
	private String sigla;

	/**
	 * Contrutor padrão do Enum {@link TipoPessoa}
	 * 
	 * @param id
	 * @param descricao
	 * @param sigla
	 */
	private TipoPessoa(final Long id, final String descricao, final String sigla) {
		this.id = id;
		this.descricao = descricao;
		this.sigla = sigla;
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * Retorna {@link TipoPessoa} segundo ID informado
	 * 
	 * @param descricao
	 * @return {@link TipoPessoa}
	 */
	public static TipoPessoa getTipoPessoaPorId(final Long id) {
		if(Objects.isNull(id)) return null;

		return Arrays.asList(TipoPessoa.values()).stream().filter(o -> o.getId() == id).findFirst().orElse(null);
	}

	/**
	 * Retorna {@link TipoPessoa} segundo sigla informada
	 * 
	 * @param sigla
	 * @return {@link TipoPessoa}
	 */
	public static TipoPessoa getTipoPessoaPorSigla(final String sigla) {
		if(StringUtils.isEmpty(sigla)) return null;

		return Arrays.asList(TipoPessoa.values()).stream().filter(o -> o.getSigla().equalsIgnoreCase(sigla)).findFirst().orElse(null);
	}

	/**
	 * Retorna {@link TipoPessoa} segundo descrição informada
	 * 
	 * @param descricao
	 * @return {@link TipoPessoa}
	 */
	public static TipoPessoa getTipoPessoaPorDescricao(final String descricao) {
		if(StringUtils.isEmpty(descricao)) return null;

		return Arrays.asList(TipoPessoa.values()).stream().filter(o -> o.getDescricao().equalsIgnoreCase(descricao)).findFirst().orElse(null);
	}

}
