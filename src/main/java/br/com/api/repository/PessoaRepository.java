package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.model.Pessoa;

/**
 * Interface de comunicação com o banco referente a entidade {@link Pessoa}
 * 
 * @author Leonardo Araújo
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
