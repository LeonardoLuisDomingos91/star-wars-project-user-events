package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.repository.UserRepository;
import br.com.invillia.lyon.userevents.client.SwapiClient;
import br.com.invillia.lyon.userevents.domain.entity.User;
import br.com.invillia.lyon.userevents.mapper.UserMapper;
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

    private final SwapiClient swapiClient;

    public void create(final Long id) {
        System.out.println("Entrou");
        if (userRepository.findByStarWarsId(id).isPresent()){
            log.info("M=create, I=Usuário atualizado, id={}", id );
            return;
        }

        final UserResponse userResponse = swapiClient.findUser(id);

        final User user = userMapper.fromUserResponseToUser(userResponse, id);

        userRepository.save(user);
        log.info("M=create, I=Usuário salvo no banco, id={}", user.getStarWarsId());
    }
}
