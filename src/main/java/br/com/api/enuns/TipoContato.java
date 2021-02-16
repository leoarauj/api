package br.com.api.enuns;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.util.StringUtils;

/**
 * Enum com possíveis valores para Tipos de Contatos.
 * 
 * @author Leonardo Araújo
 */
public enum TipoContato {

	TELEFONE_FIXO(1L, "Telefone Fixo", "FONE_FIXO"),
	TELEFONE_CELULAR(2L, "Telefone Celular", "FONE_CELULAR"),
	FAX(3L, "Fax", "FAX"),
	EMAIL(4L, "E-mail", "EMAIL");

	private Long id;
	private String descricao;
	private String sigla;

	/**
	 * @param id
	 * @param descricao
	 * @param sigla
	 */
	private TipoContato(Long id, String descricao, String sigla) {
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
	 * Retorna {@link TipoContato} segundo descrição informada
	 * 
	 * @param descricao
	 * @return {@link TipoContato}
	 */
	public static TipoContato getTipoContatoPorDescricao(final String descricao) {
		if(StringUtils.isEmpty(descricao)) return null;

		return Arrays.asList(TipoContato.values()).stream().filter(o -> o.getDescricao().equalsIgnoreCase(descricao)).findFirst().orElse(null);
	}

	/**
	 * Retorna {@link TipoContato} segundo sigla informada
	 * 
	 * @param sigla
	 * @return {@link TipoContato}
	 */
	public static TipoContato getTipoContatoPorSigla(final String sigla) {
		if(StringUtils.isEmpty(sigla)) return null;

		return Arrays.asList(TipoContato.values()).stream().filter(o -> o.getSigla().equalsIgnoreCase(sigla)).findFirst().orElse(null);
	}

	/**
	 * Retorna {@link TipoContato} segundo ID informado
	 * 
	 * @param descricao
	 * @return {@link TipoContato}
	 */
	public static TipoContato getTipoContatoPorId(final Long id) {
		if(Objects.isNull(id)) return null;

		return Arrays.asList(TipoContato.values()).stream().filter(o -> o.getId() == id).findFirst().orElse(null);
	}
}
