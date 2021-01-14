package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.Repository.UserRepository;
import br.com.invillia.lyon.userevents.client.SwapiClient;
import br.com.invillia.lyon.userevents.domain.User;
import br.com.invillia.lyon.userevents.mapper.UserMapper;
import br.com.invillia.lyon.userevents.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final SwapiClient swapiClient;

    public UserService(final UserRepository userRepository, final UserMapper userMapper, final SwapiClient swapiClient) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.swapiClient = swapiClient;
    }

    public void create(final String id) {
        Long idUser = Long.parseLong(id);
        if(userRepository.findByIdUser(idUser).isPresent()){
            log.info("Usuário já existe");
            return;
        }

        final UserResponse userResponse = swapiClient.findUser(id);

        final  User user = userMapper.fromUserResponseToUser(userResponse, id);

        userRepository.save(user);
        log.info("Usuário salvo");
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
