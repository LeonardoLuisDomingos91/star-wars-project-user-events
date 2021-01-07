package br.com.invillia.lyon.userevents.Repository;

import br.com.invillia.lyon.userevents.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdUser(Long id);

}
