package br.com.invillia.lyon.userevents.mapper;

import br.com.invillia.lyon.userevents.domain.User;
import br.com.invillia.lyon.userevents.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromUserResponseToUser(final UserResponse userResponse, final String id) {
        User user = new User();

        user.setIdUser(Long.parseLong(id));
        user.setName(userResponse.getName());
        user.setGender(userResponse.getGender());
        user.setHeight(userResponse.getHeight());
        user.setCreated(userResponse.getCreated());
        user.setEdited(userResponse.getEdited());

        return user;
    }
}
