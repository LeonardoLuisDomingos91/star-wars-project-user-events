package br.com.invillia.lyon.userevents.service;

import br.com.invillia.lyon.userevents.Repository.PersonRepository;
import br.com.invillia.lyon.userevents.domain.User;
import br.com.invillia.lyon.userevents.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SwapiService {

    @Autowired
    private UserService userService;

    public static String BASE_URL = "https://swapi.dev/api/people/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private PersonRepository personRepository;

    public void findPerson(String id) {
        String url = BASE_URL + id + "/";
        try {
            UserResponse userResponse = restTemplate
                    .getForObject(url, UserResponse.class);

            log.info("M=findPerson, I=acessando a api,");

            //personRepository.save(user);
            userService.sendUpdate(userResponse, id);
        }catch (HttpClientErrorException h) {
            if(h.getStatusCode().equals("404")) log.info("personagem não encontrado");
        }catch (Exception e) {
            log.info("serviço fora");
        }
    }
}
