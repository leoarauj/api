package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.model.Perfil;

/**
 * Interface de comunicação com o banco referente a entidade {@link Perfil}
 * 
 * @author Leonardo Araújo
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Perfil findByPermissao(String permissao);
}
