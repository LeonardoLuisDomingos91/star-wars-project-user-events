package br.com.invillia.lyon.userevents.mapper;

import br.com.invillia.lyon.userevents.domain.entity.User;
import br.com.invillia.lyon.userevents.response.UserResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserMapper {

    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public User fromUserResponseToUser(final UserResponse userResponse, final Long id) {
        User user = new User();
        System.out.println("id"+id);
        user.setStarWarsId(id);
        user.setName(userResponse.getName());
        user.setGender(userResponse.getGender());
        user.setHeight(userResponse.getHeight());
        user.setCreated(timestamp);
        user.setEdited(timestamp);

        return user;
    }
}
