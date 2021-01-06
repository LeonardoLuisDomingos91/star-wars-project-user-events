package br.com.invillia.lyon.userevents.mapper;

import br.com.invillia.lyon.userapi.events.UserEvent;
import br.com.invillia.lyon.userevents.domain.User;
import br.com.invillia.lyon.userevents.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User updateUser(UserResponse userResponse, String id) {
        User user = new User();
        //UserEvent userEvent = new UserEvent();

        user.setId_user(Long.parseLong(id));
        user.setName(userResponse.getName());
        user.setGender(userResponse.getGender());
        user.setHeight(userResponse.getHeight());
        user.setCreated(userResponse.getCreated());
        user.setEdited(userResponse.getEdited());

        return user;
    }
}
