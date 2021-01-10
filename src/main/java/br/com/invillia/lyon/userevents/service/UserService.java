package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.Repository.UserRepository;
import br.com.invillia.lyon.userevents.domain.User;
import br.com.invillia.lyon.userevents.mapper.UserMapper;
import br.com.invillia.lyon.userevents.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void sendUpdate(UserResponse userResponse, String id) {
        User user = userMapper.settingValuesForUser(userResponse, id);
        saveUser(user);
    }

    public void saveUser(User user) {
       Boolean check = getIdUser(user.getIdUser());

       if(check != true){
           userRepository.save(user);
           log.info("M=saveUser, I=Usuario salvo");
       }else{
           log.info("M=saveUser, I=Usuario já existe");
       }
    }

    public Boolean getIdUser(Long id) {
       Optional<User> optionalUser = userRepository.findByIdUser(id);
        return idempotency(optionalUser);
    }

    public Boolean idempotency(Optional user) {
        if(user.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
