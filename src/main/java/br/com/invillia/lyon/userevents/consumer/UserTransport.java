package br.com.invillia.lyon.userevents.consumer;

import br.com.invillia.lyon.userapi.events.UserEvent;
import br.com.invillia.lyon.userevents.channel.UserTransportChannel;
import br.com.invillia.lyon.userevents.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserTransport {

    private final UserService userService;

    public UserTransport(UserService userService) {
        this.userService = userService;
    }

    @StreamListener(UserTransportChannel.INPUT)
    public void consumeUser(final UserEvent userEvent) {
        log.info("M=consumeUser, I=consumindo evento, id={}", userEvent.getId());

        userService.create(userEvent.getId());
    }
}
