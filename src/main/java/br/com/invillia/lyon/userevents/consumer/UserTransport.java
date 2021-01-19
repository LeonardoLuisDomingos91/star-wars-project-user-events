package br.com.invillia.lyon.userevents.consumer;

import br.com.invillia.lyon.userapi.events.UserEvent;
import br.com.invillia.lyon.userevents.channel.UserTransportChannel;
import br.com.invillia.lyon.userevents.client.SwapiClient;
import br.com.invillia.lyon.userevents.response.UserResponse;
import br.com.invillia.lyon.userevents.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserTransport {

    private final UserService userService;

    private final SwapiClient swapiClient;

    public UserTransport(UserService userService, SwapiClient swapiClient) {
        this.userService = userService;
        this.swapiClient = swapiClient;
    }

    @StreamListener(UserTransportChannel.INPUT)
    public void consumeUser(final UserEvent userEvent) {
        log.info("M=consumeUser, I=consumindo evento, id={}", userEvent.getId());

        final UserResponse userResponse = swapiClient.findUser(userEvent.getId());

        log.info("bucando dados na api");

        userService.create(userResponse, userEvent.getId());
    }
}
