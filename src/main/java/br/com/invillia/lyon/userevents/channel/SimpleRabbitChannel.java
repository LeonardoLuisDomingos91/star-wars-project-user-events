package br.com.invillia.lyon.userevents.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

@Service
public interface SimpleRabbitChannel {

    String SIMPLE_RABBIT_INPUT = "simple-rabbit-input";

    String SIMPLE_RABBIT_OUTPUT = "simple-rabbit-output";

    @Input(SIMPLE_RABBIT_INPUT)
    SubscribableChannel input();

    @Output(SIMPLE_RABBIT_OUTPUT)
    MessageChannel output();
}
