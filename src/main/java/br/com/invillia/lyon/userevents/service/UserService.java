package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userapi.events.UserEvent;
import br.com.invillia.lyon.userevents.Repository.PersonRepository;
import br.com.invillia.lyon.userevents.domain.User;
import br.com.invillia.lyon.userevents.mapper.UserMapper;
import br.com.invillia.lyon.userevents.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserMapper userMapper;

    public void sendUpdate(UserResponse userResponse, String id) {
        User user = userMapper.updateUser(userResponse, id);
        saveUser(user);
    }

    public void saveUser(User user) {
       Boolean check = searchAllUsersInTheBank(user.getId_user());

       if(check != true){
           personRepository.save(user);
           log.info("M=saveUser, I=Usuario salvo");
       }else{
           log.info("M=saveUser, I=Usuario já existe");
       }
    }

    public Boolean searchAllUsersInTheBank(long id) {
        List<User> userList = new ArrayList<>();
        userList = personRepository.findAll();

        return idempotency(userList, id);
    }

    public Boolean idempotency(List<User> list, long id) {
        for (User user: list) {

            if (user.getId_user() != id){
                continue;
            }else{
                return true;
            }

        }
             return false;
    }
}
