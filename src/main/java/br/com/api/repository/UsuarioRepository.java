package br.com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.enuns.StatusAtivoInativo;
import br.com.api.model.Perfil;
import br.com.api.model.Pessoa;
import br.com.api.model.Usuario;

/**
 * Interface de comunicação com o banco referente a entidade {@link Usuario}
 * 
 * @author Leonardo Araújo
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);

	Usuario findByPessoa(Pessoa pessoa);

	List<Usuario> findByStatus(StatusAtivoInativo status);

	List<Usuario> findByPerfis(Perfil perfil);
}
