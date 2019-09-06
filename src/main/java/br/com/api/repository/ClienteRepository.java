package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.model.Cliente;

/**
 * Interface de comunicação com o banco referente a entidade {@link Cliente}
 * 
 * @author Leonardo Araújo
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
