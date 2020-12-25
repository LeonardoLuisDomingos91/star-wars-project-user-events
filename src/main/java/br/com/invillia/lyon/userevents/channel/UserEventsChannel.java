package br.com.invillia.lyon.userevents.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

@Service
public interface UserEventsChannel {

    String INPUT = "user-input";

    @Input(INPUT)
    SubscribableChannel inputBoundUsers();
}
