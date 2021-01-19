package br.com.invillia.lyon.userevents.repository;

import br.com.invillia.lyon.userevents.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByStarWarsId(final Long id);

}
