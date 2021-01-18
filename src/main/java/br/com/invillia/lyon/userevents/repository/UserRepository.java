package br.com.invillia.lyon.userevents.repository;

import br.com.invillia.lyon.userevents.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByStarWarsId(final Long id);

}
