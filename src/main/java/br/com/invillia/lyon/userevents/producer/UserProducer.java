package br.com.invillia.lyon.userevents.producer;

import br.com.invillia.lyon.userevents.channel.SimpleRabbitChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final SimpleRabbitChannel simpleRabbitChannel;

    public UserProducer(SimpleRabbitChannel simpleRabbitChannel) {
        this.simpleRabbitChannel = simpleRabbitChannel;
    }

    public void produce(final Long StarWarsId) {

        Message<Long> messageEmployee = MessageBuilder
                .withPayload(StarWarsId)
                .build();
        simpleRabbitChannel.output().send(messageEmployee);
    }
}
