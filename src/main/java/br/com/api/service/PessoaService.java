package br.com.api.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.model.Pessoa;
import br.com.api.repository.PessoaRepository;

/**
 * Classe de implementação das regras de negócio referente a {@link Pessoa}
 * 
 * @author Leonardo Araújo
 */
@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	/**
	 * Função que implementa as regras de negócio referente a persistência da
	 * entidade {@link Pessoa}
	 * 
	 * @param pessoa
	 * @return {@link Pessoa}
	 */
	public Pessoa salvar(final Pessoa pessoa) {
		validarCamposObrigatorios(pessoa);

		return pessoaRepository.save(pessoa);
	}

	/**
	 * Busca {@link Pessoa} segundo ID informado
	 * 
	 * @param id
	 * @return
	 */
	public Pessoa getById(final Long id) {
		return Objects.isNull(id) ? null : pessoaRepository.findById(id).get();
	}

	/**
	 * Realiza a validação de campos obrigatórios da entidade {@link Pessoa}
	 * 
	 * @param pessoa
	 */
	private void validarCamposObrigatorios(final Pessoa pessoa) {
		if(Objects.isNull(pessoa)
				|| Objects.isNull(pessoa.getCpfCnpj())
				|| Objects.isNull(pessoa.getDataInicio())
				|| Objects.isNull(pessoa.getDenominacao())
				|| Objects.isNull(pessoa.getTipoPessoa())) {
			
			// Throw
		}
	}
}
