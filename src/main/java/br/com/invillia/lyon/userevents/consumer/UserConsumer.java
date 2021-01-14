package br.com.invillia.lyon.userevents.consumer;

import br.com.invillia.lyon.userapi.events.UserEvent;
import br.com.invillia.lyon.userevents.channel.UserEventsChannel;
import br.com.invillia.lyon.userevents.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserConsumer {

    private final UserService userService;

    public UserConsumer(UserService userService) {
        this.userService = userService;
    }

    @StreamListener(UserEventsChannel.INPUT)
    public void consumeUser(final UserEvent userEvent) {
        log.info("M=consumeUser, I=logando o id consumido, String={}", userEvent.getId());

        userService.create(userEvent.getId());

    }
}
