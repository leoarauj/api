package br.com.api.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.enuns.StatusAtivoInativo;
import br.com.api.model.Cliente;
import br.com.api.repository.ClienteRepository;

/**
 * Classe de implementação das regras de negócio referente a {@link Cliente}
 * 
 * @author Leonardo Araújo
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Função que implementa as regras de negócio referente a persistência da
	 * entidade {@link Cliente}
	 * 
	 * @param cliente
	 * @return {@link Cliente}
	 */
	public Cliente salvar(final Cliente cliente) {
		validarCamposObrigatorios(cliente);

		return clienteRepository.save(cliente);
	}

	/**
	 * Busca {@link Cliente} segundo ID informado
	 * 
	 * @param id
	 * @return
	 */
	public Cliente getById(final Long id) {
		return Objects.isNull(id) ? null : clienteRepository.findById(id).get();
	}

	/**
	 * Altera o status do {@link Cliente} para <b>INATIVO</b>
	 * 
	 * @param id
	 */
	public void inativar(final Long id) {
		Cliente cliente = getById(id);

		if(Objects.isNull(cliente)) {
			// Throw
		}

		if(cliente.getStatus() == StatusAtivoInativo.ATIVO) {
			cliente.setStatus(StatusAtivoInativo.INATIVO);
			validarCamposObrigatorios(cliente);
			clienteRepository.save(cliente);
		}
	}

	/**
	 * Altera o status do {@link Cliente} para <b>ATIVO</b>
	 * 
	 * @param id
	 */
	public void ativar(final Long id) {
		Cliente cliente = getById(id);

		if(Objects.isNull(cliente)) {
			// Throw
		}

		if(cliente.getStatus() == StatusAtivoInativo.INATIVO) {
			cliente.setStatus(StatusAtivoInativo.ATIVO);
			validarCamposObrigatorios(cliente);
			clienteRepository.save(cliente);
		}
	}

	/**
	 * Realiza a validação de campos obrigatórios da entidade {@link Cliente}
	 * 
	 * @param pessoa
	 */
	private void validarCamposObrigatorios(final Cliente cliente) {
		if(Objects.isNull(cliente) 
				|| Objects.isNull(cliente.getPessoa())
				|| Objects.isNull(cliente.getStatus())
				|| Objects.isNull(cliente.getDataInicio())) {

			// Throw
		}
	}
}
