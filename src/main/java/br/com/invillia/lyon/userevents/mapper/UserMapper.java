package br.com.invillia.lyon.userevents.mapper;

import br.com.invillia.lyon.userevents.domain.entity.UserEntity;
import br.com.invillia.lyon.userevents.response.UserResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserMapper {

    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public UserEntity fromUserResponseToUser(final UserResponse userResponse, final Long id) {
        UserEntity userEntity = new UserEntity();
        System.out.println("id"+id);
        userEntity.setStarWarsId(id);
        userEntity.setName(userResponse.getName());
        userEntity.setGender(userResponse.getGender());
        userEntity.setHeight(userResponse.getHeight());
        userEntity.setCreated(timestamp);
        userEntity.setEdited(timestamp);

        return userEntity;
    }
}
