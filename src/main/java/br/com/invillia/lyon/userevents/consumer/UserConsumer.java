package br.com.invillia.lyon.userevents.consumer;

import br.com.invillia.lyon.userapi.events.UserEvent;
import br.com.invillia.lyon.userevents.channel.UserTransportChannel;
import br.com.invillia.lyon.userevents.client.StarWarsClient;
import br.com.invillia.lyon.userevents.domain.entity.UserEntity;
import br.com.invillia.lyon.userevents.repository.UserRepository;
import br.com.invillia.lyon.userevents.response.UserResponse;
import br.com.invillia.lyon.userevents.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserConsumer {

    private final UserRepository userRepository;

    private final UserService userService;

    private final StarWarsClient starWarsClient;

    public UserConsumer(UserRepository userRepository, UserService userService, StarWarsClient starWarsClient) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.starWarsClient = starWarsClient;
    }

    @StreamListener(UserTransportChannel.INPUT)
    public void consume(final UserEvent userEvent) {
        log.info("M=consumeUser, I=consumindo evento, id={}", userEvent.getId());

        final UserResponse userResponse = starWarsClient.findUser(userEvent.getId());

        Optional<UserEntity> optionalUser = userRepository.findByStarWarsId(userEvent.getId());
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userService.update(userResponse, userEntity);
            return;
        }

        userService.create(userResponse, userEvent.getId());
    }
}
