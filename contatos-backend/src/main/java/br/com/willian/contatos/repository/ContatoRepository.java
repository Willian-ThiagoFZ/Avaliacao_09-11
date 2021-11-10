package br.com.willian.contatos.repository;

import br.com.willian.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

    Optional<Contato> findContatoByEmail(String email);

    Optional<Contato> findContatoByTelefone(String telefone);

}
