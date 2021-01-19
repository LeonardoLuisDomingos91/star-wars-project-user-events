package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.repository.UserRepository;
import br.com.invillia.lyon.userevents.client.SwapiClient;
import br.com.invillia.lyon.userevents.domain.entity.UserEntity;
import br.com.invillia.lyon.userevents.mapper.UserMapper;
import br.com.invillia.lyon.userevents.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final SwapiClient swapiClient;

    public void create(final UserResponse userResponse, Long id) {
        Optional<UserEntity> optionalUser = userRepository.findByStarWarsId(id);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            UserEntity userEntityUpdate = update(userResponse, userEntity, id);
            userRepository.save(userEntityUpdate);
            log.info("M=create, I=Usuário atualizado, id={}", id );
            return;
        }

        final UserEntity userEntity = userMapper.fromUserResponseToUser(userResponse, id);

        userRepository.save(userEntity);
        log.info("M=create, I=Usuário salvo no banco, id={}", userEntity.getStarWarsId());
    }

    private UserEntity update(final UserResponse userResponse, final UserEntity userEntity, final Long id) {
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        userEntity.setStarWarsId(id);
        userEntity.setName(userResponse.getName());
        userEntity.setGender(userResponse.getGender());
        userEntity.setHeight(userResponse.getHeight());
        userEntity.setEdited(timestamp);
        return userEntity;
    }
}
