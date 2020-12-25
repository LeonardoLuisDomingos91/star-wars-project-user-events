package br.com.invillia.lyon.userevents.consumer;


import br.com.invillia.lyon.userapi.events.User;
import br.com.invillia.lyon.userevents.channel.UserEventsChannel;
import br.com.invillia.lyon.userevents.service.SwapiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventsConsumer {

    public Logger logger = LoggerFactory.getLogger(UserEventsConsumer.class);

    @Autowired
    private SwapiService swapiService;

    @StreamListener(UserEventsChannel.INPUT)
    public void sendUser(User userEvents) {

        swapiService.findPerson(userEvents.getId());

        System.out.println("ID consumido:" + userEvents.getId());

        logger.info("M=sendUser, I=logando o id consumido, String={}", userEvents.getId());
    }
}
