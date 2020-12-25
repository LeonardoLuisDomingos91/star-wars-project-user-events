package br.com.invillia.lyon.userevents.Repository;

import br.com.invillia.lyon.userevents.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
