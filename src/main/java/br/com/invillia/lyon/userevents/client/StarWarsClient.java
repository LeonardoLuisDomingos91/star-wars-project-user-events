package br.com.invillia.lyon.userevents.client;

import br.com.invillia.lyon.userevents.repository.UserRepository;
import br.com.invillia.lyon.userevents.response.UserResponse;
import br.com.invillia.lyon.userevents.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class StarWarsClient {

    @Autowired
    private UserService userService;

    public static String BASE_URL = "https://swapi.dev/api/people/";

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserRepository userRepository;

    public UserResponse findUser(final Long starWarsId) {
        log.info("M=findUser, I=bucando usuário na api, starWarsId{}", starWarsId);
        String url = BASE_URL + starWarsId + "/";
        UserResponse userResponse = new UserResponse();
        try {
             userResponse = restTemplate
                    .getForObject(url, UserResponse.class);

        }catch (HttpClientErrorException h) {
            if(h.getStatusCode().equals("404")) log.info("personagem não encontrado");
        }catch (Exception e) {
            log.info("serviço fora");
        }

        return userResponse;
    }
}
