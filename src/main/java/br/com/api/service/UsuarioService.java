package br.com.api.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.enuns.StatusAtivoInativo;
import br.com.api.exception.RestMessageCode;
import br.com.api.exception.RestResponseMessageException;
import br.com.api.model.Usuario;
import br.com.api.repository.UsuarioRepository;

/**
 * Classe de implementação das regras de negócio referente a {@link Usuario}
 * 
 * @author Leonardo Araújo
 */
@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Função que implementa as regras de negócio referente a persistência da
	 * entidade {@link Usuario}
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario salvar(final Usuario usuario) {
		validarCamposObrigatorios(usuario);

		return usuarioRepository.save(usuario);
	}

	/**
	 * Busca {@link Usuario} segundo ID informado
	 * 
	 * @param id
	 * @return
	 */
	public Usuario buscarPorId(final Long id) {
		return Objects.isNull(id) ? null : usuarioRepository.findById(id).orElse(null);
	}

	/**
	 * Busca {@link Usuario} segundo Login informado
	 * 
	 * @param login
	 * @return
	 */
	public Usuario buscarPorLogin(final String login) {
		return usuarioRepository.findByLogin(login);
	}

	/**
	 * Retorna uma Lista de {@link Usuario} segundo {@link StatusAtivoInativo}
	 * 
	 * @param status
	 * @return
	 */
	public List<Usuario> buscarPorStatus(StatusAtivoInativo status) {
		return usuarioRepository.findByStatus(status);
	}

	/**
	 * Altera o {@link StatusAtivoInativo} para <b>ATIVO</b> do {@link Usuario}
	 * segundo id informado
	 * 
	 * @param id
	 */
	public void ativarUsuario(final Long id) {
		if (Objects.isNull(id))
			throw new RestResponseMessageException(RestMessageCode.CAMPOS_OBRIGATORIOS_NAO_INFORMADOS);

		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new RestResponseMessageException(RestMessageCode.NENHUM_RESULTADO_ENCONTRADO));

		if(usuario.getStatus().getId().compareTo(StatusAtivoInativo.INATIVO.getId()) == 0) {
			usuario.setStatus(StatusAtivoInativo.ATIVO);
			usuarioRepository.save(usuario);
		}
	}

	/**
	 * Altera o {@link StatusAtivoInativo} para <b>INATIVO</b> do {@link Usuario}
	 * segundo id informado
	 * 
	 * @param id
	 */
	public void inativarUsuario(final Long id) {
		if (Objects.isNull(id))
			throw new RestResponseMessageException(RestMessageCode.CAMPOS_OBRIGATORIOS_NAO_INFORMADOS);

		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new RestResponseMessageException(RestMessageCode.NENHUM_RESULTADO_ENCONTRADO));

		if(usuario.getStatus().getId().compareTo(StatusAtivoInativo.ATIVO.getId()) == 0) {
			usuario.setStatus(StatusAtivoInativo.INATIVO);
			usuarioRepository.save(usuario);
		}
	}

	/**
	 * Realiza a validação de campos obrigatórios da entidade {@link Usuario}
	 * 
	 * @param usuario
	 */
	private void validarCamposObrigatorios(final Usuario usuario) {
		if(Objects.isNull(usuario)
				|| Objects.isNull(usuario.getLogin())
				|| Objects.isNull(usuario.getSenha())
				|| Objects.isNull(usuario.getPessoa())
				|| Objects.isNull(usuario.getStatus())
				|| Objects.isNull(usuario.getDataInicio())) {

			throw new RestResponseMessageException(RestMessageCode.CAMPOS_OBRIGATORIOS_NAO_INFORMADOS);
		}
	}
}
