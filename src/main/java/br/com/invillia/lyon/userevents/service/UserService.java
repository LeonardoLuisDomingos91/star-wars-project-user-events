package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.repository.UserRepository;
import br.com.invillia.lyon.userevents.client.SwapiClient;
import br.com.invillia.lyon.userevents.domain.entity.User;
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
        Optional<User> optionalUser = userRepository.findByStarWarsId(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User userUpdate = update(userResponse, user, id);
            userRepository.save(userUpdate);
            log.info("M=create, I=Usuário atualizado, id={}", id );
            return;
        }

        final User user = userMapper.fromUserResponseToUser(userResponse, id);

        userRepository.save(user);
        log.info("M=create, I=Usuário salvo no banco, id={}", user.getStarWarsId());
    }

    private User update(final UserResponse userResponse, final User user, final Long id) {
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        user.setStarWarsId(id);
        user.setName(userResponse.getName());
        user.setGender(userResponse.getGender());
        user.setHeight(userResponse.getHeight());
        user.setEdited(timestamp);
        return user;
    }
}
