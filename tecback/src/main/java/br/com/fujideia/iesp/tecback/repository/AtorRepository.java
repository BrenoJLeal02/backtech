package br.com.fujideia.iesp.tecback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fujideia.iesp.tecback.model.Ator;
@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
}
