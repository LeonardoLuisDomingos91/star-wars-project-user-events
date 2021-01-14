package br.com.invillia.lyon.userevents.client;

import br.com.invillia.lyon.userevents.Repository.UserRepository;
import br.com.invillia.lyon.userevents.response.UserResponse;
import br.com.invillia.lyon.userevents.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SwapiClient {

    @Autowired
    private UserService userService;

    public static String BASE_URL = "https://swapi.dev/api/people/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserRepository userRepository;

    public UserResponse findUser(final String id) {
        String url = BASE_URL + id + "/";
        UserResponse userResponse = new UserResponse();
        try {
             userResponse = restTemplate
                    .getForObject(url, UserResponse.class);

            log.info("M=findPerson, I=acessando a api,");

        }catch (HttpClientErrorException h) {
            if(h.getStatusCode().equals("404")) log.info("personagem não encontrado");
        }catch (Exception e) {
            log.info("serviço fora");
        }

        return userResponse;
    }
}
