package br.com.invillia.lyon.userevents;

import br.com.invillia.lyon.userevents.channel.SimpleKafkaChannel;
import br.com.invillia.lyon.userevents.channel.SimpleRabbitChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({
		SimpleKafkaChannel.class,
		SimpleRabbitChannel.class
})
public class UserEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserEventsApplication.class, args);
	}
}
