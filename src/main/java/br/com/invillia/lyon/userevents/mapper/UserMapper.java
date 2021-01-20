package br.com.invillia.lyon.userevents.mapper;

import br.com.invillia.lyon.userevents.domain.entity.UserEntity;
import br.com.invillia.lyon.userevents.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity fromUserResponseToUser(final UserResponse userResponse, final Long starWarsId) {
        UserEntity userEntity = new UserEntity();

        userEntity.setStarWarsId(starWarsId);
        userEntity.setName(userResponse.getName());
        userEntity.setGender(userResponse.getGender());
        userEntity.setHeight(userResponse.getHeight());
        return userEntity;
    }
}
