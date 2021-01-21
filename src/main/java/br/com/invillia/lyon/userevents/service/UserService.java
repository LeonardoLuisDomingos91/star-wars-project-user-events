package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.domain.entity.UserEntity;
import br.com.invillia.lyon.userevents.mapper.UserMapper;
import br.com.invillia.lyon.userevents.repository.UserRepository;
import br.com.invillia.lyon.userevents.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public void create(final UserResponse userResponse, final Long starWarsId) {
        log.info("M=create, I= criando usuário, starWarsId={}", starWarsId);
        final UserEntity userEntity = userMapper.fromUserResponseToUser(userResponse, starWarsId);

        userRepository.save(userEntity);
        log.info("M=create, I=Usuário salvo no banco, starWarsId={}", starWarsId);
    }

    public void update(final UserResponse userResponse, final UserEntity userEntity) {
        log.info("M=create, I= atualizando usuário, starWarsId={}", userEntity.getStarWarsId());
        userEntity.setName(userResponse.getName());
        userEntity.setGender(userResponse.getGender());
        userEntity.setHeight(userResponse.getHeight());
        userEntity.preUpdate();
        userRepository.save(userEntity);
        log.info("M=create, I=usuário atualizado, starWarsId={}", userEntity.getStarWarsId());
    }
}
