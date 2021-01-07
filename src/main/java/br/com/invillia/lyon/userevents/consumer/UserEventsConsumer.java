package br.com.invillia.lyon.userevents.consumer;

import br.com.invillia.lyon.userapi.events.UserEvent;
import br.com.invillia.lyon.userevents.channel.UserEventsChannel;
import br.com.invillia.lyon.userevents.service.SwapiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventsConsumer {

    @Autowired
    private SwapiService swapiService;

    @StreamListener(UserEventsChannel.INPUT)
    public void consumeUser(UserEvent userEvent) {

        swapiService.findUser(userEvent.getId());

        log.info("M=consumeUser, I=logando o id consumido, String={}", userEvent.getId());
    }
}
